package com.opencsi.ghrm.services

import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage

class MailService {

    static transactional = true

    def sendMail(String who,String subject,String content) {
        def mailSender = appContext.getBean( "mailSender" )
        def message = new SimpleMailMessage()

        message.from = "noreply@opencsi.com"
        message.to = who
        message.subject = subject

        message.body = content
        
        try{
            mailSender.send(message)
        }
        catch(MailException Ex)
        {
            log.info = "Error to send the email"
        }
    }
}
