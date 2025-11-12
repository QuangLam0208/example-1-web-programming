package ltweb.service;

import ltweb.model.User;

public interface UserService {

	User get(String username);

	User login(String username, String password);

}
