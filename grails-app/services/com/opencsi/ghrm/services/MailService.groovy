package com.opencsi.ghrm.services

import org.apache.commons.mail.*
import org.codehaus.groovy.grails.commons.*

// For using the MailService class, you need to install the plugin mail for grails:
// CLI : grails install-plugin mail
class MailService {

    static transactional = true

    def sendMail(String who,String subject,String content) {
        def config = ConfigurationHolder.config// enable the method to use datas into Config.groovy
        
        Email email = new SimpleEmail()
        email.setHostName(config.hostNameSMTP)// "smtp.opencsi.com"
        email.setSmtpPort(config.hostPortSMTP)// 25
        email.setAuthenticator(new DefaultAuthenticator(config.hostLoginSMTP,
                                    config.hostPasswordSMTP))
        email.setTLS(true) // need a true certificat
        email.setFrom(config.hostFromSMTP)
        email.setSubject(subject)
        email.setMsg(content)
        email.addTo(who)
        try
        {
            email.send()
        }
        catch(Throwable t)
        {
            return "Error during sending the email."
        }
        return "A email notification has been sent to '$who'."
    }
}
