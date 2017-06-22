package com.mgr2.dto.convertion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mgr2.dto.UserDTO;
import com.mgr2.model.User;

@Service("userDTOConverter")
public class UserDTOConverter {
	
	public UserDTO convertModelToDTO(User user){
		UserDTO uDTO = new UserDTO();
		uDTO.setId(user.getId());
		uDTO.setEmail(user.getEmail());
		//uDTO.setPassword(user.getPassword());
		uDTO.setName(user.getName());
		uDTO.setLastname(user.getLastName());
		uDTO.setActive(user.getActive());
		uDTO.setPoints(user.getPoints());
		return uDTO;
	}
	
	public User convertDTOtoModel(UserDTO uDTO){
		User user = new User();
		user.setId(uDTO.getId());
		user.setEmail(uDTO.getEmail());
		user.setPassword(uDTO.getPassword());
		user.setName(uDTO.getName());
		user.setLastName(uDTO.getLastname());
		user.setActive(uDTO.getActive());
		user.setPoints(uDTO.getPoints());
		return user;
	}
	
	public User convertDTOtoModel(UserDTO uDTO, User user){
		user.setId(uDTO.getId());
		user.setEmail(uDTO.getEmail());
		user.setPassword(uDTO.getPassword());
		user.setName(uDTO.getName());
		user.setLastName(uDTO.getLastname());
		user.setActive(uDTO.getActive());
		user.setPoints(uDTO.getPoints());
		return user;
	}
	
	public List<UserDTO> convertListOfModelsToDTOList(List<User> users){
		List<UserDTO> uDTO = new ArrayList<>();
		for(User u : users){
			uDTO.add(convertModelToDTO(u));
		}
		return uDTO;
	}

}
