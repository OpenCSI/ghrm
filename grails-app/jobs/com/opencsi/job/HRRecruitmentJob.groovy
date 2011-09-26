package com.opencsi.job

import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

import com.opencsi.ghrm.domain.Recruitment
import com.opencsi.ghrm.domain.StatutRecruitment

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
                    def From = (it.getFrom().toString().split(" "))
                    new Recruitment(who: '['+From[1].toString(),title: it.getSubject(),comment: it.getContent().toString(),
                       statut: StatutRecruitment.get(1),user: User.get(1),file:it.getFileName()==null?'No file':it.getFileName(),
                       createat : today.toDate(),updateat : today.toDate()).save(failOnError:true)
                    it.setFlag(Flags.Flag.DELETED, true)
                }
                println("[HRRecruitment JOB] : Done!")
            }
            else
                println("[HRRecruitment JOB] : No new message!")
        }
    }
}
