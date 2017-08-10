package subservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SecretQuestion;
import model.User;
import service.SecretQuestionService;
import servlet.MasterServlet;

/**
 * Servlet implementation class LoginServlet
 */
public class SignUpServlet {

	public static final String URL = "/SignUpServlet";
	
    private SignUpServlet() { }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
    	System.out.println("SIGNUP SERVLET  GET");
    	
	}

	private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
System.out.println("SIGNUP SERVLET POST");
		
		String idNum = request.getParameter(User.COL_IDNUMBER);
		System.out.println("ID Num : " + idNum);
		
		System.out.println("Setting Secret Questions");
		
		/* Set Page Attributes
		 * -secret questions
		 * -id number
		 */
		
		request.getSession().setAttribute("secretQuestions", getSecretQuestions());
		request.getSession().setAttribute(User.COL_IDNUMBER, idNum);
		
		//redirect to Registration
		request.getRequestDispatcher("/WEB-INF/secured/registration.jsp").forward(request, response);
	}
	
	public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}
	
	private static ArrayList<String> getSecretQuestions()
	{
		ArrayList<SecretQuestion> secretQuestions = SecretQuestionService.getAllSecretQuestions();
		ArrayList<String> strSecretQuestions = new ArrayList<String> ();
		for(int i = 0; i < secretQuestions.size(); i++)
		{
			strSecretQuestions.add(secretQuestions.get(i).getQuestion());
		}
		return strSecretQuestions;	
	}


}
