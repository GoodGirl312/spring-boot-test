package cn.ys.shop.utils;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String to, String code)
			throws AddressException, MessagingException {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		//props.setProperty("mail.transport.protocol", "SMTP");
		//props.setProperty("mail.host", "localhost");
		//props.setProperty("mail.smtp.auth", "true");// 指定验证为true
		props.setProperty("mail.smtp", "localhost");

		// 创建验证器
		Authenticator auth = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						"service@shop.com", "service");
			}
		};

		Session session = Session.getInstance(props, auth);

		// 2.创建一个Message，它相当于是邮件内容
		Message message = new MimeMessage(session);
		// 设置发送者
		message.setFrom(new InternetAddress("itcast_duhong@sohu.com"));

		// 设置发送方式与接收者
		message.setRecipient(RecipientType.TO, new InternetAddress(to));

		//设置标题
		message.setSubject("来自shop的用户激活");

		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");


		//设置正文
		message.setContent("<h1>来自shop的用户激活</h1><a href='" +
				"http://169.254.160.178:8080/shop/user/active?code="+
				code+"'>请点击激活,激活码为："+code+"</a>",
				"text/html;charset=utf-8");

		// 3.创建 Transport用于将邮件发送

		Transport.send(message);
	}
}
