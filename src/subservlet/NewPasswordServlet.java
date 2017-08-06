package subservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.MasterServlet;

/**
 * Servlet implementation class LoadSecretQuestionServlet
 */

public class NewPasswordServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/NewPasswordServlet";
       
    public NewPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("NEW PASSWORD GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("NEW PASSWORD POST");
       	     request.getRequestDispatcher("change-pwd.jsp").forward(request, response);
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		else doPost(request, response);
	}
    


}