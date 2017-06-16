package com.mgr2.dto.convertion;

import java.util.ArrayList;
import java.util.List;

import com.mgr2.dto.UserDTO;
import com.mgr2.model.User;

public class UserDTOConverter {
	
	public static UserDTO convertModelToDTO(User user){
		UserDTO uDTO = new UserDTO();
		uDTO.setId(user.getId());
		uDTO.setEmail(user.getEmail());
		uDTO.setPassword(user.getPassword());
		uDTO.setName(user.getName());
		uDTO.setLastname(user.getLastName());
		uDTO.setActive(user.getActive());
		return uDTO;
	}
	
	public static User convertDTOtoModel(UserDTO uDTO){
		User user = new User();
		user.setId(uDTO.getId());
		user.setEmail(uDTO.getEmail());
		user.setPassword(uDTO.getPassword());
		user.setName(uDTO.getName());
		user.setLastName(uDTO.getLastname());
		user.setActive(uDTO.getActive());
		return user;
	}
	
	public static List<UserDTO> convertSetOfModelsToDTO(List<User> users){
		List<UserDTO> uDTO = new ArrayList<>();
		for(User u : users){
			uDTO.add(convertModelToDTO(u));
		}
		return uDTO;
		
	}

}
