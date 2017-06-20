package com.mgr2.service;

import com.mgr2.dto.ChangePassDTO;
import com.mgr2.dto.UserDTO;
import com.mgr2.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUserOld(User user);
	public String saveUser(UserDTO userDTO);
	public String updateUser(UserDTO userDTO);
	public String buyTraining(int user_id, int training_id);
	public String giveAdmin(int userId);
	public String updateUserPass(ChangePassDTO pDTO);
}