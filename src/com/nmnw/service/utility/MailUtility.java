package com.nmnw.service.utility;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import com.nmnw.service.constant.ConfigConstants;

public class MailUtility {

	/**
	 * メール送信
	 * @param sendTo
	 * @param subject
	 * @param message
	 * @return
	 */
	public static boolean sendMail (String sendTo, String subject, String message) {
		boolean sendResult = true;
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", ConfigConstants.SMTP_HOST);
			properties.put("mail.smtp.port", ConfigConstants.SMTP_PORT);
			properties.put("mail.smtp.auth", ConfigConstants.SMTP_AUTH);
			// SMTP認証
			Session session = Session.getInstance(properties, new myAuth());
			MimeMessage msg = new MimeMessage(session);
			// from
			msg.setFrom(new InternetAddress(ConfigConstants.MAIL_FROM));
			// to
			InternetAddress[] address = InternetAddress.parse(sendTo);
			msg.setRecipients(Message.RecipientType.TO, address);
			// subject
			msg.setSubject(subject, ConfigConstants.CHARACTER_CODE);
			// message
			msg.setText(message, ConfigConstants.CHARACTER_CODE);
			msg.setSentDate(new Date());
			//　送信
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
			sendResult = false;
		}
		return sendResult;
	}
}

/**
 * SMTP認証クラス
 * @author ssugawara
 *
 */
class myAuth extends Authenticator {
	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
		return new javax.mail.PasswordAuthentication(ConfigConstants.SMTP_USER, ConfigConstants.SMTP_PASSWORD);
	}
}
