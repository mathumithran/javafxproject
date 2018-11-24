package application;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class send_mail {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;

	public void send(String[] toEmails,String emailBody) throws AddressException,
			MessagingException {

		send_mail javaEmail = new send_mail();

		javaEmail.setMailServerProperties();
		javaEmail.createEmailMessage(toEmails,emailBody);
		javaEmail.sendEmail();
	}

	public void setMailServerProperties() {

		String emailPort = "587";//gmail's smtp port

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	public void createEmailMessage(String[] toEmails,String emailBody) throws AddressException,
			MessagingException {
		//String[] toEmails = { "gangachitra1@gmail.com" };
		String emailSubject = "SBI Net-Banking";
		//String emailBody = "This is an email sent by sbi.";

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");//for a html email
		//emailMessage.setText(emailBody);// for a text email

	}

	public void sendEmail() throws AddressException, MessagingException {

		String emailHost = "smtp.gmail.com";
		String fromUser = "sbinetbanking1721";//just the id alone without @gmail.com
		String fromUserEmailPassword = "sbibank1997";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
		System.out.println("Email sent successfully.");
	}

}


