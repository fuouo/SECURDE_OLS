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
			for(int i = 0; i < cookies.length; i ++) {
				System.out.println(cookies[i].getName());
				if(cookies[i].getName().equals(User.COL_IDNUMBER)) {
					user = UserService.viewProfileUser(cookies[i].getValue());
				}
			}
		}
		
		request.getSession().setAttribute("isLoggedIn", user!=null);

		return user;	
	}

	public static void deleteAllCookies(HttpServletRequest request, HttpServletResponse response) {
		// get all cookies
		Cookie[] cookies = request.getCookies();
		
		System.out.println("Cookies: " + cookies.length);

		if(cookies!=null)
		{
			// delete all cookies
			for(int i = 0; i < cookies.length; i ++) {
				cookies[i].setMaxAge(0);
				cookies[i].setValue(null);
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		
		// invalidate session
		request.getSession().invalidate();
		
		System.out.println("Cookies: " + cookies.length);
	}

}
