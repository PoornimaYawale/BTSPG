package com.project.findPg.ControllerTests;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.findPg.Entities.User;
import com.project.findPg.controller.UserController;
import com.project.findPg.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
	
	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@Test
	void testHello() throws Exception {
	    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	    mockMvc.perform(MockMvcRequestBuilders.get("/api/user/hello"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.content().string("Hello World"));

	    verifyNoInteractions(userService);
	}

	@Test
	void testFindUserByEmail() throws Exception {
	    String email = "test@example.com";
	    User user = new User();
	    user.setEmail(email);

	    when(userService.findUserByEmail(email)).thenReturn(user);

	    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	    mockMvc.perform(MockMvcRequestBuilders.get("/api/user/getByEmail/{email}", email))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(email));

	    verify(userService, times(1)).findUserByEmail(email);
	}

	@Test
	void testCreateUser() throws Exception {
	    String username = "john.doe";
	    String email = "john.doe@example.com";
	    String password = "Password@12";

	    User user = new User();
	    user.setUserName(username);
	    user.setEmail(email);
	    user.setPassword(password);

	    when(userService.addUser(user)).thenReturn(user);

	    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	    mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content("{\"userName\":\"" + username + "\", \"email\":\"" + email + "\", \"password\":\"" + password + "\"}"))
	            .andExpect(MockMvcResultMatchers.status().isOk());

	    verify(userService, times(1)).addUser(user);
	}


	@Test
	void testGetUsers() throws Exception {
	    List<User> users = new ArrayList<>();
	    users.add(new User());
	    users.add(new User());

	    when(userService.getUsers()).thenReturn(users);

	    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	    mockMvc.perform(MockMvcRequestBuilders.get("/api/user/getAll"))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

	    verify(userService, times(1)).getUsers();
	}

	@Test
	void testGetUser() throws Exception {
	    int userId = 1;
	    User user = new User();
	    user.setUserId(userId);

	    when(userService.getUser(userId)).thenReturn(user);

	    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	    mockMvc.perform(MockMvcRequestBuilders.get("/api/user/getById/{userId}", userId))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(userId));

	    verify(userService, times(1)).getUser(userId);
	}

	@Test
	void testGetUsersByRole() throws Exception {
	    String role = "admin";
	    List<User> users = new ArrayList<>();
	    users.add(new User());
	    users.add(new User());

	    when(userService.findUserByRole(role)).thenReturn(users);

	    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	    mockMvc.perform(MockMvcRequestBuilders.get("/api/user/getByRole/{role}", role))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

	    verify(userService, times(1)).findUserByRole(role);
	}

	@Test
	void testDeleteUser() throws Exception {
	    int userId = 1;

	    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	    mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/deleteById/{userId}", userId))
	            .andExpect(MockMvcResultMatchers.status().isOk());

	    verify(userService, times(1)).deleteUser(userId);
	}
	
}
