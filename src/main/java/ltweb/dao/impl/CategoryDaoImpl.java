package ltweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ltweb.model.Category;
import ltweb.config.DBSQLConnect;
import ltweb.dao.CategoryDao;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public void insert(Category category) {
		String sql = "INSERT TO [Category]([name], [images]) VALUES (?,?)";
		try (Connection conn = new DBSQLConnect().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, category.getName());
			ps.setString(2, category.getImages());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void edit(Category category) {
		String sql = "UPDATE [Category] SET [name] = ?, [images] = ? WHERE [id] = ?";
		try (Connection conn = new DBSQLConnect().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, category.getName());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM [Category] WHERE [id] = ?";
		try (Connection conn = new DBSQLConnect().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Category get(int id) {
		String sql = "SELECT * FROM [Category] WHERE [id] = ?";
		try (Connection conn = new DBSQLConnect().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Category category = new Category();
					category.setId(rs.getInt("id"));
					category.setName(rs.getString("name"));
					category.setImages(rs.getString("images"));
					return category;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> getAll() {
		List<Category> categories = new ArrayList<>();
		String sql = "SELECT * FROM [Category]";
		try (Connection conn = new DBSQLConnect().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				category.setImages(rs.getString("images"));
				categories.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}
	
	@Override
	public List<Category> search(String keyword) {
		return null;
	}

}
