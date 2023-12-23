package com.project.findPg.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.project.findPg.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	
	//Custom methods 
	
	User findUserByEmail(String email);
	
	User findUserByEmailAndPassword(String email, String password);
	
	List<User> findUserByRole(String role);

}
