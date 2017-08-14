package subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.CookieService;
import service.SecretQuestionService;
import service.UserService;
import servlet.MasterServlet;
import utils.MyLogger;
import utils.Utils;

/**
 * Servlet implementation class LoadSecretQuestionServlet
 */

public class AdminLoadSecretQuestionServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/AdminLoadSecretQuestionServlet";
       
    public AdminLoadSecretQuestionServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("ADMIN - LOAD SECRET QUESTION GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("ADMIN - LOAD SECRET QUESTION POST");
    	
    	User user = CookieService.isUser(request);
		
		if(user != null)
		{
			System.out.println("USER IS NOT NULL");
			String userName = (String) user.getFirstName() + " " + user.getLastName();
			request.getSession().setAttribute(User.COL_FIRSTNAME+User.COL_LASTNAME,
					userName);
			
			request.setAttribute("sqID", 
					SecretQuestionService.getSecretQuestionOfUser((Integer.parseInt(user.getIdnumber()))).getQuestion());
			request.setAttribute(User.COL_IDNUMBER+"_ans", 
					user.getIdnumber());
			
			MyLogger.getInstance().info("Moderator " + user.getIdnumber() + " tries to remember his/her forgotten password.");
		}
		
		request.getRequestDispatcher("/WEB-INF/secured/a-change-pwd.jsp").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}