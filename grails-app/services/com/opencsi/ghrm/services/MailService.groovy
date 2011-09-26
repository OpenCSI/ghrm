package com.opencsi.ghrm.services

import org.apache.commons.mail.*
import org.codehaus.groovy.grails.commons.*

import javax.mail.*
import javax.mail.search.*
import java.util.Properties

// For using the MailService class, you need to install the plugin mail for grails:
// CLI : grails install-plugin mail
class MailService {

    static transactional = true

    def sendMail(String who,String subject,String content) {
        def config = ConfigurationHolder.config// enable the method to use datas into Config.groovy
        try
        {
            Email email = new SimpleEmail()
            email.setHostName(config.mail.hostNameSMTP.toString())// "smtp.opencsi.com"
            email.setSmtpPort(config.mail.hostPortSMTP.toInteger())// 25
            email.setAuthenticator(new DefaultAuthenticator(config.mail.hostLoginSMTP.toString(),
                                        config.mail.hostPasswordSMTP.toString()))
            email.setTLS(true) // need a true certificat
            email.setFrom(config.mail.hostFromSMTP.toString())
            email.setSubject(subject)
            email.setMsg(content)
            email.addTo(who)
            email.send()
        }
        catch(Throwable t)
        {
            return "Error during sending the email."
        }
        return "A email notification has been sent to '$who'."
    }

    def receiveMail() {
        def config = ConfigurationHolder.config
        Properties prop = new Properties()
        try{
            prop.setProperty("mail.store.protocol","imap")
            prop.setProperty("mail.imap.host",config.mail.hostNameReceive.toString())
            prop.setProperty("mail.imap.port",config.mail.hostPortReceive.toString())

            def session = Session.getDefaultInstance(prop,null)
            def store = session.getStore("imap")
            def inbox
        
            // try to connect into the IMAP server:
            store.connect(prop.setProperty("mail.imap.host",config.mail.hostNameReceive.toString()),
                          config.mail.hostLoginReceive.toString(),config.mail.hostPasswordReceive.toString())
            // receive all the email:
            inbox = store.getFolder(config.mail.hostINBOXReceive.toString())//openFolder(store,config.mail.hostINBOXReceive.toString())
            inbox.open(Folder.READ_WRITE)
            Message[] msgs = inbox.getMessages()
            // return the mails
            return msgs
            // close when it's done
            //store.close()
        }catch(Exception e){
            return e
        }
    }
}
