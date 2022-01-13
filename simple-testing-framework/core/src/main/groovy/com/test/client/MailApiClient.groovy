package com.test.client

import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.Multipart
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class MailApiClient {

    private Session session
    private Properties prop
    private String mailFrom

    private String pathToFile = System.getProperty("user.dir") + "/src/test/resources/Geachte_mevrouw.txt"

    MailApiClient(String mailSmtpHost, String mailSmtpPort, String mailFromUsername, String mailFromPassword) {
        mailFrom = mailFromUsername
        prop = new Properties()
        prop.put("mail.smtp.auth", true)
        prop.put("mail.smtp.starttls.enable", "true")
        prop.put("mail.smtp.host", mailSmtpHost)
        prop.put("mail.smtp.port", mailSmtpPort)
        prop.put("mail.smtp.ssl.trust", mailSmtpHost)

        session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFromUsername, mailFromPassword)
            }
        })
    }

    private Message createMessage(String subject, String mailTo) {
        Message message = new MimeMessage(session)
        message.setFrom(new InternetAddress(mailFrom))
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(mailTo))
        message.setSubject(subject)
        return message
    }

    private void sendMessage(Multipart multipart, Message message){
        message.setContent(multipart)

        Transport.send(message)
    }

    void sendEmailWithSubjectAndMessage(String subject, String msg, String mailTo) {
        Message message = createMessage(subject, mailTo)

        MimeBodyPart mimeBodyPart = new MimeBodyPart()
        mimeBodyPart.setContent(msg, "text/plain")

        Multipart multipart = new MimeMultipart()
        multipart.addBodyPart(mimeBodyPart)

        sendMessage(multipart, message)
    }

    void sendEmailWithSubjectAndAttachment(String subject, String mailTo) {
        Message message = createMessage(subject, mailTo)

        MimeBodyPart mimeBodyPart = new MimeBodyPart()
        mimeBodyPart.setContent("Message with attachment", "text/html")

        MimeBodyPart attachmentBodyPart = new MimeBodyPart()
        attachmentBodyPart.attachFile(new File(pathToFile))
        Multipart multipart = new MimeMultipart()
        multipart.addBodyPart(mimeBodyPart)
        multipart.addBodyPart(attachmentBodyPart)

        sendMessage(multipart, message)
    }
}
