package ltweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ltweb.config.DBSQLConnect;
import ltweb.dao.UserDao;
import ltweb.model.User;

public class UserDaoImpl implements UserDao {
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public User get(String username) {
		String sql = "SELECT * FROM [User] WHERE username = ? ";
		
		try (Connection conn = new DBSQLConnect().getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setString(1, username);
			
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setEmail(rs.getString("email"));
					user.setUsername(rs.getString("username"));
					user.setFullname(rs.getString("fullname"));
					user.setPassword(rs.getString("password"));
					user.setAvatar(rs.getString("avatar"));
					user.setRoleid(rs.getInt("roleid"));
					user.setPhone(rs.getString("phone"));
					user.setCreateddate(rs.getDate("createddate"));
					return user; 
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void insert(User user) {
		String sql = "INSERT INTO [User](email, username, fullname, password, avatar, roleid,phone, createddate) VALUES (?,?,?,?,?,?,?,?)";

		try (Connection conn = new DBSQLConnect().getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAvatar());
			ps.setInt(6,user.getRoleid());
			ps.setString(7,user.getPhone());
			ps.setDate(8, new java.sql.Date(user.getCreateddate().getTime()));
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from [user] where email = ?";
		
		try (Connection conn = new DBSQLConnect().getConnection();
				 PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) duplicate = true;	
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from [User] where username = ?";
		try {
			conn = new DBSQLConnect().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) duplicate = true;	
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}
	
	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "select * from [User] where phone = ?";

		try (Connection conn = new DBSQLConnect().getConnection();
				 PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, phone);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) duplicate = true;	
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return duplicate;
	}
}
