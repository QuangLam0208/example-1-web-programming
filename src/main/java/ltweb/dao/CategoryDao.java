package ltweb.dao;

import java.util.List;
import ltweb.model.Category;

public interface CategoryDao {

	Category get(int id);
	
//	Category get(String name);
	
	List<Category> getAll();
	
	List<Category> search(String keyword);

	void insert(Category category);
	
	void edit(Category category);
	
	void delete(int id);
	
}
