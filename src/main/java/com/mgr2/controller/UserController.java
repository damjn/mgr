package com.mgr2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mgr2.dto.ChangePassDTO;
import com.mgr2.dto.ClientTraningDTO;
import com.mgr2.dto.TrainingDTO;
import com.mgr2.dto.UserDTO;
import com.mgr2.dto.convertion.UserDTOConverter;
import com.mgr2.repository.UserRepository;
import com.mgr2.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserDTOConverter userDTOConverter;
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "/users",method = RequestMethod.GET)
    public @ResponseBody
    List<UserDTO> getAllUsers(){
        return userDTOConverter.convertListOfModelsToDTOList(userRepository.findAll());
    }
	
	@RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public @ResponseBody UserDTO getUser(@RequestBody UserDTO uDTO){
        return userDTOConverter.convertModelToDTO(userRepository.findByEmail(uDTO.getEmail()));
    }
	
	@RequestMapping(value = "/user",method = RequestMethod.POST)
    public @ResponseBody String addUser (@RequestBody UserDTO user){
       return userService.saveUser(user);
    }
	
	@RequestMapping(value = "/user",method = RequestMethod.PUT)
    public @ResponseBody String editUser (@RequestBody UserDTO user){
       return userService.updateUser(user);
    }
	
	@RequestMapping(value = "/user_pass",method = RequestMethod.PUT)
    public @ResponseBody String changeUserPass (@RequestBody ChangePassDTO pDTO){
       return userService.updateUserPass(pDTO);
    }
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteUser(@PathVariable("id") int userId) {
		userRepository.delete(userId);
		return "USER DELETED";
	}

	@RequestMapping(value = "/buyTraining", method = RequestMethod.POST)
	public @ResponseBody String buyTraining(@RequestBody ClientTraningDTO ctDTO) {
		return userService.buyTraining(ctDTO.getUser_id(),ctDTO.getTraining_id());
	}
	
	@RequestMapping(value = "/giveAdmin/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String giveAdmin(@PathVariable("id") int userId) {
		return userService.giveAdmin(userId);
	}
	
	

}
