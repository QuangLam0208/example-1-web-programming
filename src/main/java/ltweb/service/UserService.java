package ltweb.service;

import ltweb.model.User;

public interface UserService {

	User get(String username);

	User login(String username, String password);
	
	boolean register(String email, String username, String fullname, String password, String phone);

	boolean checkExistUsername(String username);

	boolean checkExistEmail(String email);

	boolean checkExistPhone(String phone);

	void insert(User user);

}
