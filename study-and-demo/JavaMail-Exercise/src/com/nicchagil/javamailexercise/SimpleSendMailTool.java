package com.nicchagil.javamailexercise;

public class SimpleSendMailTool {
	
	 /* 必需的信息 */
    private static String SMTP_MAIL_HOST = "smtp.163.com"; // 此邮件服务器地址，自行去所属邮件服务器描述页查询
    private static String EMAIL_USERNAME = "example@163.com";
    private static String EMAIL_PASSWORD = "password";
    private static String TO_EMAIL_ADDRESS_1 = "example@163.com";

	public static void main(String[] args) throws Exception {
		if (args == null || args.length < 2) {
			throw new IllegalArgumentException();
		}
		
		/* 使用情况一，正常使用 */
        MailSenderConfig c = new MailSenderConfig(SMTP_MAIL_HOST, 
        		args[0], args[1], EMAIL_USERNAME);
        c.setUsername(EMAIL_USERNAME);
        c.setPassword(EMAIL_PASSWORD);
        c.addToMail(TO_EMAIL_ADDRESS_1);
        
        MailSender ms = new MailSender(c);
        ms.send();
        System.out.println("sent...");
	}

}
