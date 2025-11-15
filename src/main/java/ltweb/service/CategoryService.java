package ltweb.service;

import java.util.List;

import ltweb.model.Category;

public interface CategoryService {

	List<Category> getAll();

	Category get(int id);

	void delete(int id);

	void edit(Category newCategory);

	void insert(Category category);

	List<Category> search(String catename);

}
