package com.project.findPg.services;

import java.util.List;
import java.util.Optional;

import com.project.findPg.Entities.User;
import com.project.fingPg.responseAndRequest.UserRequest;

public interface UserService {
      
	User addUser(User user);
	List<User> getUsers();
	User getUser(int userId);
	void deleteUser(int userId);
	User updateUser(User user,int userId);
	
	User findUserByEmail(String email);
	User findUserByEmailAndPassword(UserRequest userRequest);
	List<User> findUserByRole(String role);
	
}
