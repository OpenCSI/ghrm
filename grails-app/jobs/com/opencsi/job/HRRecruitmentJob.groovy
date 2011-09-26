package com.opencsi.job

import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

import com.opencsi.ghrm.services.MailService
import javax.mail.Message

class HRRecruitmentJob {
    def timeout = 100000l//600000l // execute job every 10 minutes.
    MailService mail

    def execute() {

        // connection into the IMAP server in order to receive the different mails
        mail = new MailService()
        Message[] msgs = mail.receiveMail()
        if (msgs == null)
           println("[HRRecruitment JOB] : Erreur de récupération des mails")
        else
        {
           /*msgs.each{
               println(it.getContent())
           }*/
        }
        // add into database the mail, and the attached file (CV) into a directory.
    }
}
