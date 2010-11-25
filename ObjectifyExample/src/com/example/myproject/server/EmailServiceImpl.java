package com.example.myproject.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cedarleaf.client.entities.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class EmailServiceImpl extends RemoteServiceServlet {

	String adminEmail = "admin@example.com"; // insert your admin email address
												// here
	Properties props = new Properties();
	Session session = Session.getDefaultInstance(props, null);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		sendEmail();
	}

	public void sendEmail() {

		String msgBody = "This is a test email body";

		try {
			Message msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress(adminEmail, "Administrator"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"test@example.com"));
			msg.setSubject("This is a test email subject");
			msg.setText(msgBody);  //TIP: use msg.setContent to send an HTML formatted email
			Transport.send(msg);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
