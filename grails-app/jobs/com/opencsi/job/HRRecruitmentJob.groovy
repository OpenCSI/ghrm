package com.opencsi.job

import com.opencsi.ghrm.domain.*
import org.joda.time.DateTime

import com.opencsi.ghrm.services.MailService

class HRRecruitmentJob {
    def timeout = 600000l // execute job once in 10 minutes.
    MailService mail

    def execute() {
        // connection into the IMAP server in order to receive the different mails
        // add into database the mail, and the attached file (CV) into a directory.
    }
}
