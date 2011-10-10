package com.opencsi.ghrm.services

import org.codehaus.groovy.grails.commons.ConfigurationHolder
// SEND:
import org.apache.commons.mail.SimpleEmail
import org.apache.commons.mail.Email
import org.apache.commons.mail.DefaultAuthenticator
// RECEIVE:
import javax.mail.internet.MimeMessage
import java.util.Properties
import javax.mail.Session
import javax.mail.Folder

// For using the MailService class, you need to install the plugin mail for grails:
// CLI : grails install-plugin mail
class MailService {

    static transactional = true
    def config = ConfigurationHolder.config// enable the method to use datas into Config.groovy

    def sendMail(String who,String subject,String content) {
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

    def inbox
    def store

    def receiveMail() {
        Properties prop = new Properties()
        try{
            prop.setProperty("mail.store.protocol","imap")
            prop.setProperty("mail.imap.host",config.mail.hostNameReceive.toString())
            prop.setProperty("mail.imap.port",config.mail.hostPortReceive.toString())

            def session = Session.getDefaultInstance(prop,null)
            store = session.getStore("imap")
            
            // try to connect into the IMAP server:
            store.connect(prop.setProperty("mail.imap.host",config.mail.hostNameReceive.toString()),
                          config.mail.hostLoginReceive.toString(),config.mail.hostPasswordReceive.toString())
            // receive all the email:
            inbox = store.getFolder(config.mail.hostINBOXReceive.toString())//openFolder(store,config.mail.hostINBOXReceive.toString())
            inbox.open(Folder.READ_WRITE)
            MimeMessage[] msgs = inbox.getMessages()
            // return the mails
            return msgs
            // close when it's done
            //store.close()
        }catch(Exception e){
            return null
        }
    }

    def closeInbox(){
        if (inbox)
            inbox.close(true)
    }
    def closeStore(){
        if (store)
            store.close()
    }
}
