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
import ltweb.model.Category;
import ltweb.service.CategoryService;
import ltweb.service.impl.CategoryServiceImpl;
import ltweb.util.Constant;

@WebServlet(urlPatterns = "/admin/category/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class CategoryEditController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Category category = cateService.get(Integer.parseInt(id));

		req.setAttribute("category", category);
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/edit-category.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		Category category = new Category();
		category.setId(Integer.parseInt(req.getParameter("id")));
		category.setName(req.getParameter("name"));

		Part part = req.getPart("icon");
		String fileName = getFileName(part);

		// Kiểm tra xem người dùng có upload file mới không
		if (fileName != null && !fileName.isEmpty()) {
			// Có file mới -> Xử lý upload
			String finalFileName = System.currentTimeMillis() + "_" + fileName;
			String uploadPath = Constant.DIR + File.separator + "category";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			part.write(uploadPath + File.separator + finalFileName);

			// Set icon mới, ServiceImpl sẽ lo việc xóa icon cũ
			category.setImages("category" + File.separator + finalFileName);
		} else {
			// Không có file mới, giữ nguyên icon cũ (bằng cách set là null)
			category.setImages(null);
		}
		
		cateService.edit(category);
		resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	}

	private String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				String fileName = content.substring(content.indexOf("=") + 2, content.length() - 1);
				return (fileName.isEmpty() ? null : fileName);
			}
		}
		return null;
	}

}
