package ltweb.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ltweb.service.UserService;
import ltweb.service.impl.UserServiceImpl;
import ltweb.util.Constant;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotPasswordController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(Constant.FORGOT_PASS).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String email = req.getParameter("email");
		UserService service = new UserServiceImpl();
		
		if (service.checkExistEmail(email)) {
			// Email tồn tại, lưu email vào session để dùng ở ResetPasswordController
			HttpSession session = req.getSession();
			session.setAttribute("resetEmail", email);
			
			resp.sendRedirect(req.getContextPath()+"/reset-password");
		} else {
			req.setAttribute("alert", "Email does not exist!");
			req.getRequestDispatcher(Constant.FORGOT_PASS).forward(req, resp);
		}
	}

}
