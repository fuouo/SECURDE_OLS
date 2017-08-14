package subservlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.util.URI;

import model.ReadingMaterial;
import model.Review;
import model.User;
import model.UserType;
import service.CookieService;
import service.EmailService;
import service.ReadingMaterialService;
import service.ReviewService;
import service.SecretQuestionService;
import service.UserService;
import servlet.MasterServlet;
import utils.MyLogger;
import utils.Utils;

public class PasswordChangeServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/PasswordChangeServlet";
       
    public PasswordChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("PASSWORD CHANGE GET");
    	String password1 = request.getParameter(User.COL_PASSWORD);
    	String password2 = request.getParameter("confirm_" + User.COL_PASSWORD);
    	
    	System.out.println("Matching " + password1 + "with" + password2);
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("PASSWORD CHANGE POST");
    	String password1 = request.getParameter(User.COL_PASSWORD);
    	String password2 = request.getParameter("confirm_" + User.COL_PASSWORD);
    	
    	System.out.println("Matching " + password1 + "with" + password2);
    	
    	if(password1.equals(password2))
    	{
    		String idNumber = request.getParameter(User.COL_IDNUMBER);
    		System.out.println("Password Match");
    		System.out.println(idNumber + "Id Number");

			String idnumber_hashed = Utils.get_SHA_256_SecureString(idNumber, "");
			User user = UserService.viewProfileUser(idnumber_hashed);
			user.setPassword(password2);
			UserService.changePassword(user);
			
			UserService.changePasswordToActivateAccount(user);
			MyLogger.getInstance().info("User " + user.getIdnumber() + " changed password successfully!");
    	}
    	else {
    		MyLogger.getInstance().severe("Password did not match!");
    	}
    	
    	request.getRequestDispatcher("StartServlet").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}
    


}