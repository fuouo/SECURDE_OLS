package subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.SecretQuestionService;
import service.UserService;
import servlet.MasterServlet;
import utils.Utils;

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
		String idnumber_hashed = Utils.get_SHA_256_SecureString(idNumber, "");
		System.out.println("Hashed id number: " + idnumber_hashed);
				
		User user = UserService.viewProfileUser(idnumber_hashed);
		System.out.println(user);
		
		if(user != null)
		{
			System.out.println("USER IS NOT NULL");
			request.setAttribute("sqID", 
					SecretQuestionService.getSecretQuestionOfUser((Integer.parseInt(idNumber))).getQuestion());
			request.setAttribute(User.COL_IDNUMBER+"_ans", 
					user.getIdnumber());
		}
		
		request.getRequestDispatcher("/forgot-pwd.jsp").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}


}