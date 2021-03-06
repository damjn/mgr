package com.mgr2.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mgr2.dto.ChangePassDTO;
import com.mgr2.dto.UserDTO;
import com.mgr2.dto.convertion.UserDTOConverter;
import com.mgr2.model.Role;
import com.mgr2.model.Task;
import com.mgr2.model.Training;
import com.mgr2.model.User;
import com.mgr2.model.UserTask;
import com.mgr2.repository.RoleRepository;
import com.mgr2.repository.TaskRepository;
import com.mgr2.repository.TrainingRepository;
import com.mgr2.repository.UserRepository;
import com.mgr2.repository.UserTaskRepository;
import com.mgr2.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private TrainingRepository trainingRepository;
	@Autowired
	private UserDTOConverter userDTOConverter;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserTaskRepository userTaskRepository;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUserOld(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("CLIENT");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public String buyTraining(int user_id, int training_id) {
		User user = userRepository.findOne(user_id);
		Training training = trainingRepository.findOne(training_id);
		Set<Training> trainings = user.getTrainings();
		trainings.add(training);
		user.setTrainings(trainings);
		userRepository.save(user);
		return "TRAINING BOUGHT";
	}

	@Override
	public String saveUser(UserDTO userDTO) {
		User user = userDTOConverter.convertDTOtoModel(userDTO);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByRole("CLIENT");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		List<Task> tList = taskRepository.findAll();
		Set<UserTask> uts = user.getTasks();
		for(Task t : tList){
			UserTask ut = new UserTask();
			ut.setUser(user);
			ut.setTask(t);
			ut.setCreatedDate(new Date());
			ut.setDone(0);
			String token = UUID.randomUUID().toString();
			ut.setToken(token);
			userTaskRepository.save(ut);
			//uts.add(ut); chyba to by nie zadzialalo
		}
		userRepository.save(user);
		return "USER ADDED";
	}

	@Override
	public String updateUser(UserDTO userDTO) {
		User user = userRepository.findById(userDTO.getId());
		user = userDTOConverter.convertDTOtoModel(userDTO, user);
		userRepository.save(user);
		return "USER UPDATED";
	}

	@Override
	public String giveAdmin(int userId) {
		User user = userRepository.findOne(userId);
		Role adminRole = roleRepository.findByRole("ADMIN");
		Set<Role> roles = user.getRoles();
		roles.add(adminRole);
		user.setRoles(roles);
		return null;
	}

	@Override
	public String updateUserPass(ChangePassDTO pDTO) {
		User user = userRepository.findByEmail(pDTO.getEmail());
		String oldPassFromUser = bCryptPasswordEncoder.encode(pDTO.getOldPass());
		if (oldPassFromUser.equals(user.getPassword())) {
			user.setPassword(bCryptPasswordEncoder.encode(pDTO.getNewPass()));
			userRepository.save(user);
			return "USER PASS UPDATED";
		} else {
			return "WRONG PASSWORD";
		}
	}

}