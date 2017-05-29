package com.nicchagil.mailsender;

import java.util.ArrayList;
import java.util.List;

public class MailSenderConfig {
    
    private String SMTPMailHost; // 支持SMTP协议的邮件服务器地址

    /* 用于登录邮件服务器 */
    private String username;
    private String password;

    private String subject; // 标题
    private String content; // 内容
    
    private String fromMail; // 显示从此邮箱发出邮件
    private List<String> toMails; // 收件人
    private List<String> ccMails; // 抄送人
    private List<String> bccMails; // 秘密抄送人
    private List<Attachment> attachments; // 附件
    private String contentType = "text/html;charset=utf-8";
    
    /**
     * 构造器
     * @param sMTPMailHost    SMTP服务器
     * @param subject        标题
     * @param content        内容（默认以“text/html;charset=utf-8”形式发送）
     * @param fromMail        发送人地址
     */
    public MailSenderConfig(String sMTPMailHost, String subject,
            String content, String fromMail) {
        super();
        SMTPMailHost = sMTPMailHost;
        this.subject = subject;
        this.content = content;
        this.fromMail = fromMail;
    }
    
    /**
     * 构造器
     * @param sMTPMailHost    SMTP服务器
     * @param username        邮件服务器用户名
     * @param password        邮件服务器密码
     * @param subject        标题
     * @param content        内容（默认以“text/html;charset=utf-8”形式发送）
     * @param fromMail        发送人地址
     */
    public MailSenderConfig(String sMTPMailHost, String username,
            String password, String subject, String content, String fromMail) {
        super();
        SMTPMailHost = sMTPMailHost;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
        this.fromMail = fromMail;
    }

    public void addToMail (String mail) {
        if (this.toMails == null) {
            this.toMails = new ArrayList<String>();
        }
        this.toMails.add(mail);
    }
    
    public void addCcMail (String mail) {
        if (this.ccMails == null) {
            this.ccMails = new ArrayList<String>();
        }
        this.ccMails.add(mail);
    }
    
    public void addBccMail (String mail) {
        if (this.bccMails == null) {
            this.bccMails = new ArrayList<String>();
        }
        this.bccMails.add(mail);
    }
    
    public void addAttachment (Attachment a) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<Attachment>();
        }
        this.attachments.add(a);
    }
    
    /*
     * Getter and Setter
     */
    
    public String getSMTPMailHost() {
        return SMTPMailHost;
    }

    public void setSMTPMailHost(String sMTPMailHost) {
        SMTPMailHost = sMTPMailHost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    public List<String> getToMails() {
        return toMails;
    }

    public void setToMails(List<String> toMails) {
        this.toMails = toMails;
    }

    public List<String> getCcMails() {
        return ccMails;
    }

    public void setCcMails(List<String> ccMails) {
        this.ccMails = ccMails;
    }

    public List<String> getBccMails() {
        return bccMails;
    }

    public void setBccMails(List<String> bccMails) {
        this.bccMails = bccMails;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
