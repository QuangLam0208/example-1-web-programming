package ltweb.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.model.User;
import ltweb.service.UserService;
import ltweb.service.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_USERNAME = "username";
	public static final String COOKIE_REMEMBER = "username";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			resp.sendRedirect(req.getContextPath()+ "/waiting");
			return;
		}

		// Check cookie
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(COOKIE_REMEMBER)) { 
					String username = cookie.getValue();
					UserService service = new UserServiceImpl();
					User user = service.get(username); 
					
					if (user != null) {
						session = req.getSession(true);
						session.setAttribute("account", user); 
						resp.sendRedirect(req.getContextPath()+ "/waiting");
						return;
					}
				}
			}
		}
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean isRememberMe = false;
		
		String remember = req.getParameter("rememberMe"); 

		if("on".equals(remember)){
			isRememberMe = true;
		}

		String alertMsg="";

		if(username.isEmpty() || password.isEmpty()){
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		}

		UserService service = new UserServiceImpl();
		User user = service.login(username, password);
		if(user != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);
			if(isRememberMe) {
				saveRemeberMe(req, resp, username);
			}
			resp.sendRedirect(req.getContextPath() + "/waiting");
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}

	private void saveRemeberMe(HttpServletRequest req, HttpServletResponse response, String username){
		Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
		cookie.setMaxAge(30*60);
		cookie.setPath(req.getContextPath() + "/");
		response.addCookie(cookie);
	}
}