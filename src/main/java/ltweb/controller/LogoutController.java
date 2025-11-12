package ltweb.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// 1. Đăng ký Servlet này với đường dẫn "/logout"
@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        
        if (session != null) {
            session.invalidate();
        }
        
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // Phải gọi hằng số từ LoginController
                if (cookie.getName().equals(LoginController.COOKIE_REMEMBER)) {
                    cookie.setMaxAge(0); // Set tuổi = 0 để xóa cookie
                    // Path phải khớp với path lúc bạn tạo cookie
                    cookie.setPath(req.getContextPath() + "/"); 
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}