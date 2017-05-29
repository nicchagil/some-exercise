package com.nicchagil.mailsender;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailSender extends AbstractSessionMailSender {
    
    private MailSenderConfig c;
    private MimeMessage message;
    
    public MailSender(MailSenderConfig config) throws Exception {
        super();
        this.c = config;
        this.setConfig();
    }
    
    public MailSender(MailSenderConfig config, Session session) throws Exception {
        super();
        this.c = config;
        this.setConfig();
        super.setSession(session);
    }
    
    /**
     * 发送邮件
     * @throws MessagingException
     */
    public void send() throws MessagingException {
        Transport.send(message);
    }
    
    /**
     * 获取MimeMessage，暴露此对象以便于开发者自行设置个性化的属性（此工具类不支持的方法可由开发人员自行设置，设置完毕设置回来）
     * @return
     */
    public MimeMessage getMessage() {
        return message;
    }

    /**
     * 设置MimeMessage，暴露此对象以便于开发者自行设置个性化的属性（此工具类不支持的方法可由开发人员自行设置，设置完毕设置回来）
     * @return
     */
    public void setMessage(MimeMessage message) {
        this.message = message;
    }
    
    /**
     * 设置Java Mail属性
     * @throws Exception
     */
    private void setConfig() throws Exception {
        this.configValid();
        
        if (session == null) {
            session = initSession(c);
        }
        message = new MimeMessage(session);
        
        /* 发件人 */
        Address[] fromMailArray = new Address[1];
        fromMailArray[0] = new InternetAddress(c.getFromMail());
        message.addFrom(fromMailArray);
        
        if (c.getToMails() != null && c.getToMails().size() > 0) {
            for (String mail : c.getToMails()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
            }
        }
        if (c.getCcMails() != null && c.getCcMails().size() > 0) {
            for (String mail : c.getCcMails()) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(mail));
            }
        }
        if (c.getToMails() != null && c.getToMails().size() > 0) {
            for (String mail : c.getToMails()) {
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(mail));
            }
        }
        
        // 邮件标题
        message.setSubject(c.getSubject());
        
        /* 正文 */
        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(c.getContent(), c.getContentType());
        
        /* 封装邮件各部分信息 */
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        message.setContent(multipart);
        
        /* 附件 */
        BodyPart attachmentPart = null;
        DataSource ds = null;
        if (c.getAttachments() != null && c.getAttachments().size() > 0) {
            for (Attachment a : c.getAttachments()) {
                attachmentPart = new MimeBodyPart();
                ds = new FileDataSource(a.getFile());
                attachmentPart.setDataHandler(new DataHandler(ds));
                attachmentPart.setFileName(MimeUtility.encodeText(a.getFilename()));
                
                multipart.addBodyPart(attachmentPart);
            }
        }
        
        message.setContent(multipart);
    }
    
    /**
     * 配置校验
     * @throws Exception
     */
    private void configValid() throws Exception {
        if (c == null) {
            throw new Exception("配置对象为空");
        }
        
        if (c.getSMTPMailHost() == null || c.getSMTPMailHost().length() == 0) {
            throw new Exception("SMTP服务器为空");
        }
        
        if (c.getFromMail() == null && c.getFromMail().length() == 0) {
            throw new Exception("发件人邮件为空");
        }
        
        if (c.getToMails() == null || c.getToMails().size() < 1) {
            throw new Exception("收件人邮件为空");
        }
        
        if (c.getSubject() == null || c.getSubject().length() == 0) {
            throw new Exception("邮件标题为空");
        }
        
        if (c.getContent() == null || c.getContent().length() == 0) {
            throw new Exception("邮件内容为空");
        }
    }
    
}
