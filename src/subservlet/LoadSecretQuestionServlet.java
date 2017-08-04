package subservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.ReadingMaterial;
import model.Review;
import model.User;
import model.UserType;
import service.CookieService;
import service.ReadingMaterialService;
import service.ReviewService;
import service.SecretQuestionService;
import service.UserService;
import servlet.MasterServlet;

/**
 * Servlet implementation class LoadSecretQuestionServlet
 */

public class LoadSecretQuestionServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/LoadSecretQuestionServlet";
       
    public LoadSecretQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("LOAD SECRET QUESTION GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("LOAD SECRET QUESTION POST");
    	
		String idNumber = (String) request.getParameter(User.COL_IDNUMBER);
		User user = UserService.viewProfileUser(idNumber);
		if(user != null){
			String secretQuestion = SecretQuestionService.getSecretQuestionOfUser((Integer.parseInt(idNumber))).getQuestion();
			System.out.println(secretQuestion);
			PrintWriter pw = response.getWriter();
			pw.write(secretQuestion);
		}
		
		
		
		//request.getRequestDispatcher("forgot-pwd.jsp").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}


}