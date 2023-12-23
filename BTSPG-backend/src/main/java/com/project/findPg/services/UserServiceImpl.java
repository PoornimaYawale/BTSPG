package com.project.findPg.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.findPg.Entities.User;
import com.project.findPg.Exception.EmptyListException;
import com.project.findPg.Exception.WrongCredentialsException;
import com.project.findPg.Exception.DetailNotFoundException;
import com.project.findPg.Exception.EmptyInputFieldException;
import com.project.findPg.Repositories.UserRepository;
import com.project.fingPg.responseAndRequest.UserRequest;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	 Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User addUser(User user) {
		if (user.getUserName().isBlank() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
			throw new EmptyInputFieldException();
		}
		User createdUser = userRepository.save(user);
		logger.info("User created..");
		return createdUser;
	}

	@Override
	public List<User> getUsers() {

		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new EmptyListException("user list is empty , please add user !!!");
		} else {
			return users;
		}
	}

	@Override
	public User getUser(int userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new DetailNotFoundException("user not exist in the Db for userId", userId));
		
		return user;

	}

	@Override
	public void deleteUser(int userId) {

		User user = getUser(userId);
		userRepository.delete(user);
//		if (userRepository.findById(userId).isPresent()) {
//
//			userRepository.deleteById(userId);
//		} else {
//			throw new DetailNotFoundException("user not exist in the Db for userId", userId);
//		}

	}

	@Override
	public User updateUser(User user, int userId) {

		User updatedUser = getUser(userId);
		// here if update user is null or the userId is not present in the DB
		// then the exception from the preview method is thrown i.e. from getUser( )s
		updatedUser.setUserName(user.getUserName());
		updatedUser.setEmail(user.getEmail());
		updatedUser.setPassword(user.getPassword());
		updatedUser.setRole(user.getRole());

		return userRepository.save(updatedUser);

	}

	// custom methods implementation
	@Override
	public User findUserByEmail(String email) {

		return userRepository.findUserByEmail(email);
	}

	@Override
	public User findUserByEmailAndPassword(UserRequest userRequest) {

		User user = userRepository.findUserByEmailAndPassword(userRequest.getEmail(),userRequest.getPassword());
		if (user == null) {
			throw new WrongCredentialsException("Email or password is not correct . please check");
		}
		return user;
	}

	@Override
	public List<User> findUserByRole(String role) {
		List<User> users = userRepository.findUserByRole(role);
		if (users.isEmpty()) {
			throw new EmptyListException("user list is empty , please add user !!!");
		} else {
			return users;
		}
	}

}
