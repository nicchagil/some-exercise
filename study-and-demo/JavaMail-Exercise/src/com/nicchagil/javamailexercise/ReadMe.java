package com.nicchagil.javamailexercise;

import java.io.File;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class ReadMe {
    
    /* 必需的信息 */
    String SMTP_MAIL_HOST = "smtp.163.com"; // 此邮件服务器地址，自行去所属邮件服务器描述页查询
    String EMAIL_USERNAME = "example@163.com";
    String EMAIL_PASSWORD = "password";
    String TO_EMAIL_ADDRESS_1 = "example@163.com";
    /* 选填的信息 */
    String TO_EMAIL_ADDRESS_2 = "example@163.com";

    @Test
    public void case1() throws Exception {
        /* 使用情况一，正常使用 */
        MailSenderConfig c = new MailSenderConfig(SMTP_MAIL_HOST, 
                "this is a mail for test java mail framework in case1.", "this is content.", EMAIL_USERNAME);
        c.setUsername(EMAIL_USERNAME);
        c.setPassword(EMAIL_PASSWORD);
        c.addToMail(TO_EMAIL_ADDRESS_1);
        c.addToMail(TO_EMAIL_ADDRESS_2);
        c.addCcMail(TO_EMAIL_ADDRESS_2);
        c.addCcMail(TO_EMAIL_ADDRESS_1);
        c.addAttachment(new Attachment(new File("d:/1.txt")));
        
        MailSender ms = new MailSender(c);
        ms.send();
        System.out.println("sent...");
    }
    
    @Test
    public void case2() throws Exception {
        /* 使用情况二，在更多情况下，工具类所作的设置并不满足需求，故将MimeMessage暴露以便于开发者自行设置个性化的属性 */
        MailSenderConfig c = new MailSenderConfig(SMTP_MAIL_HOST, 
                "this is a mail for test java mail framework in case2.", "this is content.", EMAIL_USERNAME);
        c.setUsername(EMAIL_USERNAME);
        c.setPassword(EMAIL_PASSWORD);
        c.addToMail(TO_EMAIL_ADDRESS_1);
        c.addToMail(TO_EMAIL_ADDRESS_2);
        c.addCcMail(TO_EMAIL_ADDRESS_2);
        c.addCcMail(TO_EMAIL_ADDRESS_1);
        c.addAttachment(new Attachment(new File("d:/1.txt")));
        
        MailSender ms = new MailSender(c);
        
        MimeMessage message = ms.getMessage();
        message.setContent("this is the replaced content by MimeMessage in case2.", "text/html;charset=utf-8");
        ms.setMessage(message);
        
        ms.send();
        System.out.println("sent...");
    }
    
    @Test
    public void case3() throws Exception {
        /* 使用情况三，多次发送邮件，可缓存Session，使多次发送邮件均共享此Session，以减少重复创建Session
         * 同时需注意缓存的Session的时效性
         */
        MailSenderConfig c = new MailSenderConfig(SMTP_MAIL_HOST, 
                "this is the first mail for test java mail framework to share session in case3.", "this is content.", EMAIL_USERNAME);
        c.setUsername(EMAIL_USERNAME);
        c.setPassword(EMAIL_PASSWORD);
        c.addToMail(TO_EMAIL_ADDRESS_1);
        c.addToMail(TO_EMAIL_ADDRESS_2);
        c.addCcMail(TO_EMAIL_ADDRESS_2);
        c.addCcMail(TO_EMAIL_ADDRESS_1);
        c.addAttachment(new Attachment(new File("d:/1.txt")));
        
        Session session = MailSender.initSession(c);
        
        MailSender ms = new MailSender(c, session);
        ms.send();
        
        c.setSubject("this is the second mail for test java mail framework to share session in case3.");
        c.setContent("this is content 2.");
        ms = new MailSender(c, session);
        ms.send();
        System.out.println("sent...");
    }

}

