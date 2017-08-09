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

/**
 * Servlet implementation class LoadSecretQuestionServlet
 */

public class PasswordChangeCodeServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/PasswordChangeCodeServlet";
       
    public PasswordChangeCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("CODE PASSWORD GET");
    	
    	
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("CODE PASSWORD POST");
    	String code = request.getParameter("userCode");
    	System.out.println("code : " + code);
    	String inputCode = request.getParameter("user-code");
    	System.out.println("input: " + inputCode);
    	
    	if(code.equals(inputCode)){
    		System.out.println("CODE MATCH");
    		request.getRequestDispatcher("/WEB-INF/secured/change-pwd.jsp").forward(request, response);
    	}
    	else{
    		System.out.println("CODE DID NOT MATCH");
    		request.getRequestDispatcher("/HomePageServlet").forward(request, response);
    	}
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}
    


}