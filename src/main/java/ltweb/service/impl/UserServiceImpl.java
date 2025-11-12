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

}
