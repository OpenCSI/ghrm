package com.opencsi.job

import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

import com.opencsi.ghrm.domain.Recruitment
import com.opencsi.ghrm.domain.StatutRecruitment
import com.opencsi.ghrm.domain.MessageRecruitment

import com.opencsi.ghrm.services.MailService
import javax.mail.Message
import javax.mail.Flags
import java.io.InputStream

import org.joda.time.DateTime

class HRRecruitmentJob {
    def timeout = 600000l // execute job every 10 minutes.
    MailService mail

    def execute() {
        def today = new DateTime()
        // connection into the IMAP server in order to receive the different mails
        mail = new MailService()
        println("[HRRecruitment JOB] : Retreive email...")
        Message[] msgs = mail.receiveMail()
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
                    def OK = false
                    // If the request doesn't exist, add it:
                    if (!res)
                        OK = true
                    else
                    {
                        // the statut is not either New,In progress or Interview:
                        if ( !((res.statut.name == "Refused") || (res.statut.name == "Accepted")) )
                        {
                            // don't add mail into Recruitment class:
                            OK = false
                            // But adding it into the messageRecruitment class:
                            // String to convert the multipart/Alternative into String:
                            String strContent = ""
                            if (it.getContentType() =~ "multipart/ALTERNATIVE")
                                strContent = it.getContent().getBodyPart(0).getContent().toString()
                            else // not a multipart method
                                strContent = it.getContent().toString()
                            println("[HRRecruitment JOB] : Adding a DialogMessage from " + From + " ...")
                            new MessageRecruitment(titre: it.getSubject().toString(),
                                message: strContent,createat: today.toDate(),
                                recruitment: res,who: From).save(failOnError:true)
                            it.setFlag(Flags.Flag.DELETED,true)
                        }
                    }
                    // Receive email:
                    if (OK)
                    {
                        println("[HRRecruitment JOB] : Adding a new Request Mail from " + From + " ...")
                        // String to convert the multipart/Alternative into String:
                        String strContent = ""
                        if (it.getContentType() =~ "multipart/ALTERNATIVE")
                            strContent = it.getContent().getBodyPart(0).getContent().toString()
                        else // not a multipart method
                            strContent = it.getContent().toString() 
                        new Recruitment(who: From,title: it.getSubject(),comment: strContent,
                           statut: StatutRecruitment.get(1),user: User.get(1),file:it.getFileName()==null?'No file':it.getFileName(),
                           createat : today.toDate(),updateat : today.toDate()).save(failOnError:true)
                        it.setFlag(Flags.Flag.DELETED, true)
                    }
                    /*else
                        println("[HRRecruitment JOB] : Mail is already existed")*/
                }
                println("[HRRecruitment JOB] : Done!")
            }
            else
                println("[HRRecruitment JOB] : No new message!")
        }
    }
}
