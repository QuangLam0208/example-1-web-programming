package ltweb.service.impl;

import ltweb.dao.impl.UserDaoImpl;
import ltweb.model.User;
import ltweb.service.UserService;
import ltweb.dao.UserDao;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		User user = this.get(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public User get(String username) {
		return userDao.get(username);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public boolean register(String email, String username, String fullname, String password, String phone) {
		try {
			User user = new User();
			user.setEmail(email);
			user.setUsername(username);
			user.setFullname(fullname);
			user.setPassword(password);
			user.setPhone(phone);
			user.setAvatar("default.jpg"); 
			user.setRoleid(3); 
			user.setCreateddate(new java.util.Date());
		
			userDao.insert(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
