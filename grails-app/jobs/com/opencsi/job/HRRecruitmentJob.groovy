package com.opencsi.job

import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

import com.opencsi.ghrm.domain.Recruitment
import com.opencsi.ghrm.domain.StatutRecruitment
import com.opencsi.ghrm.domain.MessageRecruitment

import com.opencsi.ghrm.services.MailService
import javax.mail.internet.MimeMessage
import javax.mail.Flags
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMultipart
import javax.mail.BodyPart
import java.io.InputStream
import java.io.FileWriter
//import com.sun.mail.util.BASE64DecoderStream

import org.joda.time.DateTime

class HRRecruitmentJob {
    def timeout = 600000l // execute job every 10 minutes.
    def group = "HRRecruitment"
    MailService mail
    def grailsApplication

    // TODO | UPDATE | IMPROVE :
    def execute() {
        def today = new DateTime()
        // connection into the IMAP server in order to receive the different mails
        mail = new MailService()
        println("[HRRecruitment JOB] : Retreive email...")
        MimeMessage[] msgs = mail.receiveMail()
        if (!msgs)
           println("[HRRecruitment JOB] : Error to retreive email!")
        else
        {
            if (msgs.size() > 0)
            {
                // add into database the mail, and the attached file (CV) into a directory.
                msgs.each{
                    // test if the mail is already in the database:
                    // verify if the email is already used (statut : New,In progress or Interview):
                    def tabFrom = (it.getFrom().toString().split(" "))
                    // retreive the adress mail:
                    String From = tabFrom[tabFrom.size() -1]
                    // remove the '[<' '>]' caracters into mail:
                    From = From.replace('<','')
                    From = From.replace('>','')
                    From = From.replace('[','')
                    From = From.replace(']','')
                    def res = Recruitment.findByWho(From)
                    def OK_MailRecruitment = false
                    def OK_DialogMessage = false
                    // If the request doesn't exist then specify for a Recruitment Mail:
                    if (!res)
                    {
                        println("[HRRecruitment JOB] : Adding a new Request Mail from " + From + " ...")
                        OK_MailRecruitment = true
                        OK_DialogMessage = false
                    }
                    else
                        // the statut is not either New,In progress or Interview:
                        if ( !((res.statut.name == "Refused") || (res.statut.name == "Accepted")) )
                        {
                            println("[HRRecruitment JOB] : Adding a DialogMessage from " + From + " ...")
                                OK_DialogMessage = true
                                // don't add mail into Recruitment class:
                                OK_MailRecruitment = false
                        }
                        // String to convert the multipart/--- into String:
                        String strContent = ""
                        String strFileName = ""
                        if (it.getContentType() =~ /multipart/)
                        {
                            MimeMultipart mmultiPart = (MimeMultipart)it.getContent()
                            for(def i = 0;i < mmultiPart.getCount();i++)
                            {
                                if (mmultiPart.getBodyPart(i).getFileName() != null)
                                {
                                    // create the directory to receive CV of user:
                                    strFileName = grailsApplication.parentContext.getResource("/recruitment/" + From).file.toString() 
                                    new File(strFileName).mkdir()
                                    strFileName += '/'  + mmultiPart.getBodyPart(i).getFileName()
                                    FileWriter file = new FileWriter(strFileName,true)
                                    // If data's file is some text :
                                    if (mmultiPart.getBodyPart(i).getContent() instanceof String)
                                    {
                                        file.write(mmultiPart.getBodyPart(i).getContent().toString()
                                            ,0,mmultiPart.getBodyPart(i).getContent().toString().length())
                                    }
                                    else // other :
                                    {
                                        // TO IMPROVE:
                                        InputStream is = (InputStream)mmultiPart.getBodyPart(i).getInputStream()
                                        while (is.read() != -1)
                                            file.write(is.read())
                                        is.close()
                                        // END IMPROVE;
                                    }
                                    file.close()
                                    // name of the file :
                                    strFileName =  mmultiPart.getBodyPart(i).getFileName()
                                }
                                else
                                {
                                    if (mmultiPart.getBodyPart(i).getContent() instanceof String)
                                        strContent += mmultiPart.getBodyPart(i).getContent().toString().replaceAll("<[^>]*>", "")
                                    else
                                    {
                                        MimeMultipart mmp = (MimeMultipart)mmultiPart.getBodyPart(i).getContent()
                                        for(def j = 0;j < mmp.getCount();j++)
                                        {
                                            // escape HTML tag:
                                            strContent = mmp.getBodyPart(j).getContent().toString().replaceAll("<[^>]*>", "")
                                        }
                                    }
                                }
                            }
                        }
                        else // not a multipart method
                            strContent = it.getContent().toString()
                    
                    // Receive Mail Recruitment:
                    if ( (OK_MailRecruitment)&&(!OK_DialogMessage) )
                    {
                        new Recruitment(who: From,title: it.getSubject(),comment: strContent,
                           statut: StatutRecruitment.get(1),user: User.get(1),file:strFileName,
                           createat : today.toDate(),updateat : today.toDate()).save(failOnError:true)
                        it.setFlag(Flags.Flag.DELETED, true)
                    }
                    // Receive DialogMessage:
                    else if ( (OK_DialogMessage)&&(!OK_MailRecruitment) )
                    {
                        new MessageRecruitment(title: it.getSubject().toString(),
                            message: strContent,createat: today.toDate(),
                            recruitment: res,who: From,file:strFileName).save(failOnError:true)
                        it.setFlag(Flags.Flag.DELETED,true)
                    }
                }
                // Close inbox & store (save deleted file):
                //mail.closeInbox()
                //mail.closeStore()
                println("[HRRecruitment JOB] : Done!")
            }
            else
                println("[HRRecruitment JOB] : No new message!")
        }
    }
}
