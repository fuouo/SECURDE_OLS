package subservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RMFilter;
import model.RMType;
import model.ReadingMaterial;
import service.ReadingMaterialService;
import servlet.MasterServlet;

/**
 * Servlet implementation class DisplayAuthorServlet
 */
//@WebServlet("/DisplayAuthorServlet")
public class DisplayAuthorServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/DisplayAuthorServlet";
       
    public DisplayAuthorServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DISPLAY AUTHOR GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DISPLAY AUTHOR POST");
    	
    	ArrayList<String> authors = ReadingMaterialService.getAllAuthors();
    	
    	request.setAttribute(ReadingMaterial.COL_AUTHOR, authors);
    	request.setAttribute("numOf" + ReadingMaterial.COL_AUTHOR, authors.size());
    	
    	request.getRequestDispatcher("/WEB-INF/secured/rm-authors.jsp").forward(request, response);
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
