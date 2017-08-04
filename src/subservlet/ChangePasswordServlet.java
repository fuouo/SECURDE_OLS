package subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import servlet.MasterServlet;

public class ChangePasswordServlet{

	public static final String URL = "/ChangePasswordServlet";
	private static final long serialVersionUID = 1L;
  
    public ChangePasswordServlet() {super();}
    
    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
    	System.out.println("CHANGE PASSWORD GET");
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("CHANGE PASSWORD POST");
		
		//CHECK IF OLD PASS IS VALID
		//THEN CHANGE PASSWORD TO THE NEW ONE
		
		//THEN LOG OUT USER. REDIRECT TO LOG IN PAGE
		request.getRequestDispatcher("SignInSignUpPageServlet").forward(request, response);
		
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}

}
