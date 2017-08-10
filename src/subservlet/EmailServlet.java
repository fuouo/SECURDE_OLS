package subservlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import service.EmailService;
import service.UserService;
import servlet.MasterServlet;
import utils.Utils;

import com.sun.org.apache.xerces.internal.util.URI;

/**
 * Servlet implementation class LoadSecretQuestionServlet
 */

public class EmailServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/EmailServlet";
       
    public EmailServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("EMAIL GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("EMAIL POST");
    	
    	String idNumber = (String) request.getParameter(User.COL_IDNUMBER+"_ans");
    	String idnumber_hashed = Utils.get_SHA_256_SecureString(idNumber, "");
		String answer = (String) request.getParameter(User.COL_SQANSWER);
		User user = UserService.viewProfileUser(idnumber_hashed);
		
		if(user != null)
		{
			System.out.println(user.getFirstName() + " " + user.getLastName());
			System.out.println("Answer: " + answer);
			System.out.println("Right Answer: " + user.getSecretAnswer());
			String refererURI = new URI(request.getHeader("referer")).getPath();
			System.out.println("[FROM]" + refererURI);
			
			//FORGOT PASSWORD
            if(refererURI.contains("LoadSecretQuestionServlet"))
            {
            	final String username = "securde.shs.ols@gmail.com";
       	      	final String password = "securdeshsols";
       	        String code = null;
       	        Properties props = new Properties();
       	        props.put("mail.smtp.starttls.enable", "true");
       	        props.put("mail.smtp.auth", "true");
       	        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
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
       	                InternetAddress.parse(user.getEmail()));
       	            
       	           	message.setSubject("Forgot Password");
       	            
       	            
       	            code = EmailService.generateCode();
       	            System.out.println("[Code Generated : " + code);
       	            String codeMessage = "Hi " + user.getFirstName()+ "! \n\n" 
       	            		+ "Here's your code: \n" + code
       	            		+ "\n\nSECURDE Senior HighSchool Online Library System \n\n";
       	            System.out.println("[Email Receipient]  " + user.getEmail());
       	            System.out.println("[Email Message]  " + codeMessage);
       	            message.setText(codeMessage);
       	            
       	            //encrypt code
       	            String seed = "passwordCode";
	       	        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
	       			encryptor.setPassword(seed);
	       			String encrypted= encryptor.encrypt(code);
	       			System.out.println("Encrypted" + encrypted);
       	            
       	            
       	            request.setAttribute("userCode", encrypted);
       	           // Transport.send(message);

       	            System.out.println("[Email Service] Email Sent");
       	         
       	        }catch (MessagingException e) {
       	        	System.out.println("ERROR");
       	           // throw new RuntimeException(e);
       	        }finally {
       	            //request.setAttribute("Message", resultMessage);
       	        	request.setAttribute(User.COL_IDNUMBER, idNumber);
       	        	request.getRequestDispatcher("/WEB-INF/secured/enter-code.jsp").forward(request, response);
       	        }
       	     
            }
          
		}
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}
    


}