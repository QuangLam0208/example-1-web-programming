package ltweb.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/manager/home")
public class ManagerHomeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 2. Chuyển tiếp đến file JSP của manager
        // (Chúng ta sẽ tạo file này ở bước 2)
		RequestDispatcher rd = req.getRequestDispatcher("/views/manager/home.jsp");
		rd.forward(req, resp);
	}
}
