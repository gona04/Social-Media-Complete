package com.dao;

import com.model.User;

public interface UserDao 
{
	void registerUser(User user);
	boolean isValidEmail(String email);
	boolean isValidUserName(String userName);
	User login(User user);
	void update(User user);
	User getUserByUsername(String username);
}
