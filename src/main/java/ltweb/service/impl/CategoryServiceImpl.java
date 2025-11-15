package ltweb.service.impl;

import java.io.File;
import java.util.List;

import ltweb.dao.CategoryDao;
import ltweb.dao.impl.CategoryDaoImpl;
import ltweb.model.Category;
import ltweb.service.CategoryService;
import ltweb.util.Constant;

public class CategoryServiceImpl implements CategoryService {
	CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}

	@Override
	public void edit(Category newCategory) {
		Category oldCategory = categoryDao.get(newCategory.getId());

		oldCategory.setName(newCategory.getName());
		if (newCategory.getImages() != null) {
			// Xóa ảnh cũ
			String oldIconName = oldCategory.getImages();
			if (oldIconName != null && !oldIconName.isEmpty()) {
				File oldIcon = new File(Constant.DIR + "/" + oldIconName);
				if (oldIcon.exists()) {
					oldIcon.delete();
				}
			}
			// Cập nhật ảnh mới
			oldCategory.setImages(newCategory.getImages());
		}
		categoryDao.edit(oldCategory);
	}

	@Override
	public void delete(int id) {
		Category oldCategory = categoryDao.get(id);
		if (oldCategory != null && oldCategory.getImages() != null) {
			String oldIconName = oldCategory.getImages();
			File oldIcon = new File(Constant.DIR + "/" + oldIconName);
			if (oldIcon.exists()) {
				oldIcon.delete();
			}
		}
		categoryDao.delete(id);
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);
	}

	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}

	@Override
	public List<Category> search(String catename) {
		return categoryDao.search(catename);
	}
}
