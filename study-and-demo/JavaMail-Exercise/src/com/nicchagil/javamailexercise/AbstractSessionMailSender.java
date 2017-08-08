package com.nicchagil.javamailexercise;

import java.util.Properties;

import javax.mail.Session;

public abstract class AbstractSessionMailSender {
    
    protected Session session;
    
    /**
     * 初始化Session
     * @return
     */
    public static Session initSession(MailSenderConfig c) {
        Properties props = new Properties();
        if (c.getSMTPMailHost() != null && c.getSMTPMailHost().length() > 0) {
            props.put("mail.smtp.host", c.getSMTPMailHost());
        }
        
        if (c.getUsername() != null && c.getUsername().length() > 0 && 
                c.getPassword() != null && c.getPassword().length() > 0) {
            props.put("mail.smtp.auth", "true");
            return Session.getDefaultInstance(props, new SimpleAuthenticator(c.getUsername(), c.getPassword()));
        } else {
            props.put("mail.smtp.auth", "false");
            return Session.getDefaultInstance(props);
        }
    }

    /**
     * 暴露Getter、Setter提供Session的可设置性，以支持批量发送邮件/发送多次邮件时，可缓存Session
     * @return
     */
    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
}
