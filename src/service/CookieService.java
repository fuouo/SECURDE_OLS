package service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

public class CookieService {

	public static User isUser(HttpServletRequest request)
	{
		User user = null;

		//Check if a user is logged in
		Cookie[] cookies = request.getCookies();

		//System.out.println("[Cookies]: " + cookies.length);
		//Search specific cookie
		if(cookies!=null)
		{
			
			System.out.println("Cookie length: " + cookies.length);
			for(int i = 0; i < cookies.length; i ++) {
				System.out.println(cookies[i].getName());
				if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
					System.out.println("Cooooookies: " + cookies[i].getValue());
					user = UserService.viewProfileUser(cookies[i].getValue());
				}
			}
		}
		
		request.getSession().setAttribute("isLoggedIn", user!=null);

		return user;	
	}

}
