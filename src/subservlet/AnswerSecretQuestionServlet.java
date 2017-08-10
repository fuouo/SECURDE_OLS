package subservlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import utils.Utils;

/**
 * Servlet implementation class LoadSecretQuestionServlet
 */

public class AnswerSecretQuestionServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/AnswerSecretQuestionServlet";
       
    public AnswerSecretQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ANSWER SECRET QUESTION GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("ANSWER SECRET QUESTION POST");
    	
    	String idNumber = (String) request.getParameter(User.COL_IDNUMBER+"_ans");
		String answer = (String) request.getParameter(User.COL_SQANSWER);
		String idnumber_hashed = Utils.get_SHA_256_SecureString(idNumber, "");
		User user = UserService.viewProfileUser(idnumber_hashed);
		
		if(user != null)
		{
			System.out.println(user.getFirstName() + " " + user.getLastName());
			System.out.println("Answer: " + answer);
			System.out.println("Right Answer: " + user.getSecretAnswer());
			
			//if the user entered the correct answer
			if(answer.equals(user.getSecretAnswer()))
			{
				request.getRequestDispatcher("/EmailServlet").forward(request, response);
				//
			}
			else
				System.out.println("NOPE :( ");
			
		}
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}


}