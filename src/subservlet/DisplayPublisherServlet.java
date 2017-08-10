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
public class DisplayPublisherServlet{
	private static final long serialVersionUID = 1L;
	public static final String URL = "/DisplayPublisherServlet";
       
    public DisplayPublisherServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

    private static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DISPLAY PUBLISHER GET");
	}

    private static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.out.println("DISPLAY PUBLISHER POST");
    	
    	ArrayList<String> publishers = ReadingMaterialService.getAllPublishers();
    	
    	request.setAttribute(ReadingMaterial.COL_PUBLISHER, publishers);
    	request.setAttribute("numOf" + ReadingMaterial.COL_PUBLISHER, publishers.size());
    	
    	request.getRequestDispatcher("/WEB-INF/secured/rm-publishers.jsp").forward(request, response);
    	
	}
    
    public static void process(HttpServletRequest request, HttpServletResponse response, int type) throws ServletException, IOException{
		if(type == MasterServlet.TYPE_GET)
			doGet(request, response);
		doPost(request, response);
	}


}
