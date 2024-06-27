package cn.ruanwenjun.utils;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {

	public static void sendMail(String email, String emailMsg) throws Exception {

		// 加载配置文件
		Properties props = new Properties();
		InputStream inputStream = MailUtils.class.getClassLoader().getResourceAsStream("email.properties");
		props.load(inputStream);
		inputStream.close();

		// 从配置文件中获取邮箱配置信息
		String host = props.getProperty("mail.smtp.host");
		String auth = props.getProperty("mail.smtp.auth");
		String port = props.getProperty("mail.smtp.port");
		String socketFactoryPort = props.getProperty("mail.smtp.socketFactory.port");
		String socketFactoryClass = props.getProperty("mail.smtp.socketFactory.class");
		String username = props.getProperty("mail.username");
		String authCode = props.getProperty("mail.authcode");
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties mailProps  = new Properties();
		mailProps .setProperty("mail.smtp.host", host);
		mailProps .setProperty("mail.smtp.auth", auth);
		mailProps .setProperty("mail.smtp.port", port); // 或者使用465或587端口
		mailProps .put("mail.smtp.socketFactory.port", socketFactoryPort);
		mailProps .put("mail.smtp.socketFactory.class", socketFactoryClass);


		// 创建会话
		Session session = Session.getDefaultInstance(mailProps, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, authCode);
			}
		});

		// 创建邮件
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username)); // 设置发件人
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); // 设置收件人
		message.setSubject("用户激活");
		message.setContent(emailMsg, "text/html;charset=UTF-8"); // 设置邮件内容
		// 发送邮件
		Transport.send(message);


	}
}
