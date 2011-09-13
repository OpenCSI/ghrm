package com.opencsi.ghrm.services

import org.apache.commons.mail.*

// For using the MailService class, you need to install the plugin mail for grails:
// CLI : grails install-plugin mail
class MailService {

    static transactional = true

    def sendMail(String who,String subject,String content) {
        Email email = new SimpleEmail();
        email.setHostName("smtp.opencsi.com");
        email.setSmtpPort(25);
        email.setAuthenticator(new DefaultAuthenticator("login", "passwd"));
        email.setTLS(true); // need a true certificat
        email.setFrom("noreply@opencsi.com");
        email.setSubject(subject);
        email.setMsg(content);
        email.addTo(who);
        try
        {
            email.send();
        }
        catch(Throwable t)
        {
            return "Error during sending the email."
        }
        return "A email notification has been sent to the admin."
    }
}
