package com.mgr2.service;

import com.mgr2.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}