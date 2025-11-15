package ltweb.controller;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ltweb.service.impl.CategoryServiceImpl;
import ltweb.util.Constant;
import ltweb.model.Category;
import ltweb.service.CategoryService;

@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class CategoryAddController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		Category category = new Category();
		String cate_name = req.getParameter("name");
		Part part = req.getPart("icon"); // Lấy file từ form
		
		// Lấy tên file gốc
		String fileName = getFileName(part);
		
		// Tạo tên file duy nhất để tránh trùng lặp
		String finalFileName = System.currentTimeMillis() + "_" + fileName;
		
		// Đường dẫn thư mục upload
		String uploadPath = Constant.DIR + File.separator + "category";
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs(); // Tạo thư mục nếu chưa có
		}
		
		// Ghi file vào thư mục
		part.write(uploadPath + File.separator + finalFileName);
		
		// Lưu đường dẫn (phần tên file + thư mục con) vào DB
		category.setName(cate_name);
		category.setImages("category" + File.separator + finalFileName);
		
		cateService.insert(category);
		
		resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	}
	
	// Hàm helper để lấy tên file từ Part
	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename"))
				return content.substring(content.indexOf("=") + 2, content.length() - 1);
		}
		return "default.file"; // Hoặc trả về null/throw exception
	}
}