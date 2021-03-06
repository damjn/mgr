package com.mgr2.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mgr2.configuration.MyUserPrincipal;
import com.mgr2.dto.UserTaskDTO;
import com.mgr2.dto.UserTaskJoinDTO;
import com.mgr2.model.Task;
import com.mgr2.model.User;
import com.mgr2.model.UserTask;
import com.mgr2.model.UserTaskId;
import com.mgr2.repository.TaskRepository;
import com.mgr2.repository.UserRepository;
import com.mgr2.repository.UserTaskRepository;
import com.mgr2.service.UserTaskService;

@Service("userTaskServiceImpl")
public class UserTaskServiceImpl implements UserTaskService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserTaskRepository userTaskRepository;

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public String markUserTaskDone(UserTaskDTO utDTO) {
		UserTask ut = getUserTaskByUserIdAndTaskName(utDTO.getTaskname(), utDTO.getUser_id());
		ut.setDone(1);
		ut.setCreatedDate(new Date());
		userTaskRepository.save(ut);
		return "TASK DONE";
	}

	@Override
	public UserTask getUserTaskByUserIdAndTaskName(String taskName, int user_id) {
		User user = userRepository.findById(user_id);
		Task task = taskRepository.findByName(taskName);
		UserTaskId id = new UserTaskId();
		id.setTask(task);
		id.setUser(user);
		return userTaskRepository.findOne(id);
	}

	@Override
	public String handle24hLoginTask(int user_id) {
		User user = userRepository.findById(user_id);
		Task task = taskRepository.findByName("24hLog");
		UserTaskId id = new UserTaskId();
		id.setTask(task);
		id.setUser(user);
		UserTask ut = userTaskRepository.findOne(id);
		if (ut.getDone() == 0) {
			user.setPoints(user.getPoints() + task.getPoints());
			ut.setDone(1);
			userRepository.save(user);
			userTaskRepository.save(ut);
			return "24hLoginTask done";
		}
		return "24hLoginTask was already done";
	}

	@Override
	public String handleEmailConfirmationTask(String token) {
		UserTask ut = userTaskRepository.findByToken(token);
		User user = ut.getUser(); // mozliwe ze nie zadziala
		if (ut.getDone() == 0) {
			user.setPoints(user.getPoints() + ut.getTask().getPoints());
			ut.setDone(1);
			userRepository.save(user);
			userTaskRepository.save(ut);
			return "EmailConfirm done";
		}
		return "EmailConfirm was already done";
	}

	@Override
	public String sendEmailToConfirmUserEmail() {
		// MyUserPrincipal userPrincipal = (MyUserPrincipal)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findById(1);// HARDCODED USER WITH ID 1
												// (userPrincipal.getId()); //
												// do
												// zmiany, dodac get user do
												// MyPrincipalUser
		Task task = taskRepository.findByName("EmailConfirm");
		UserTaskId id = new UserTaskId();
		id.setTask(task);
		id.setUser(user);
		UserTask ut = userTaskRepository.findOne(id);
		String token = ut.getToken();
		String recipientAddress = user.getEmail();
		String subject = "Email Confirmation";
		String confirmationUrl = "/emailConfirm?token=" + token;
		String message = "Click link to confirm email";

		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message + " " + "http://localhost:8080" + confirmationUrl);
		mailSender.send(email);

		return "Mail was sent"; // dodac przypadek ze task jest juz zrobiony
	}

	@Override
	public String handleRecomendationLink(String token) {
		UserTask ut = userTaskRepository.findByToken(token);
		User user = ut.getUser(); // mozliwe ze nie zadziala
		user.setPoints(user.getPoints() + ut.getTask().getPoints());
		userRepository.save(user);
		return "RegistrationRecomendation done";
	}

	@Override
	public String getRecomendationLink() {
		// MyUserPrincipal userPrincipal = (MyUserPrincipal)
		// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findById(1);// HARDCODED USER WITH ID 1
												// (userPrincipal.getId()); //
												// do
												// zmiany, dodac get user do
												// MyPrincipalUser
		Task task = taskRepository.findByName("RecomendationLink");
		UserTaskId id = new UserTaskId();
		id.setTask(task);
		id.setUser(user);
		UserTask ut = userTaskRepository.findOne(id);
		String token = ut.getToken();
		return "http://localhost:8080/registration?token="+token;
	}

	@Override
	public List<UserTaskJoinDTO> getAllUserTasks() {
		MyUserPrincipal userPrincipal = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByEmail(userPrincipal.getUsername());
		List<Task> tasks = taskRepository.findAll();
		List<UserTaskJoinDTO> utj = new ArrayList<>();
		for(Task task : tasks){
			UserTask ut = getUserTask(user, task);
			UserTaskJoinDTO utjDTO = new UserTaskJoinDTO();
			utjDTO.setId(task.getId());
			utjDTO.setUserId(user.getId());
			utjDTO.setName(task.getName());
			utjDTO.setPoints(task.getPoints());
			utjDTO.setDescription(task.getDescription());
			utjDTO.setDone(ut.getDone());
			utjDTO.setToken(ut.getToken());
			utj.add(utjDTO);
		}
		return utj;
	}
	
	private UserTask getUserTask(User user, Task task){
		UserTaskId id = new UserTaskId();
		id.setTask(task);
		id.setUser(user);
		return userTaskRepository.findOne(id);
	}

}
