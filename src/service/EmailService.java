package service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import model.User;

public class EmailService {

	
	public static String sendCode(/*User user,*/ String subject)
	{
		 final String username = "securde.shs.ols@gmail.com";
	        final String password = "securdeshsols";
	        String code = null;
	        Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(username));
	            message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse("nicolle_magpale@dlsu.edu.ph"));
	            message.setSubject(subject);
	            code = generateCode();
	            System.out.println("[Code Generated : " + code);
	            String codeMessage = "Hi nikki! \n\n" 
	            		+ "Here's your code: \n" + code;
	            //System.out.println("[Email Receipient]  " + user.getEmail());
	            System.out.println("[Email Message]  " + codeMessage);
	            message.setText(codeMessage);

	            Transport.send(message);

	            System.out.println("[Email Service] Email Sent");

	        } catch (MessagingException e) {
	            throw new RuntimeException(e);
	        }
	    
		return code;
	}

	public static String generateCode()
	{
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32).substring(0, 8);
	}
	 public static void main(String[] args) {
		// sendCode("Forgot Password");
	 }
}
