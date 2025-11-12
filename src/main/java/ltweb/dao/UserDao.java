package ltweb.dao;

import ltweb.model.User;

public interface UserDao {

	User get(String username);

}
