package com.project.findPg.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.findPg.Entities.User;
import com.project.findPg.services.UserService;
import com.project.fingPg.responseAndRequest.UserRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/user")
@CrossOrigin
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping(path = "/hello")
	public String hello() {
		logger.info("hello api working");
		return "Hello World";
	}

	@GetMapping(path = "/getByEmail/{email}")
	public User findUserByEmail(@PathVariable String email) {
		return userService.findUserByEmail(email);
	}

	@PostMapping(path = "/Login")
	public User findUserByEmailAndPassword(@RequestBody UserRequest userRequest) {
		return userService.findUserByEmailAndPassword(userRequest);
	}

	@PostMapping("/create")
	public User createUser(@Valid @RequestBody User user) {

		return userService.addUser(user);
	}

	@GetMapping("/getAll")
	public List<User> getUsers() {
		return userService.getUsers();

	}

	@GetMapping("/getById/{userId}")
	public User getUser(@PathVariable int userId) {

		User user = userService.getUser(userId);
		return user;
	}

	@GetMapping("/getByRole/{role}")
	public List<User> getUsersByRole(@PathVariable String role) {

		return userService.findUserByRole(role);
	}

	@DeleteMapping("/deleteById/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);

	}

	@PutMapping("/update/{userId}")
	public User updateUser(@PathVariable int userId,@Valid @RequestBody User user) {
		user.setUserId(userId);
		return userService.updateUser(user, userId);
	}
}
