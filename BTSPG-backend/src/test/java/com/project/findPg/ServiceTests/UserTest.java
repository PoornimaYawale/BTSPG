package com.project.findPg.ServiceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.findPg.Entities.User;
import com.project.findPg.Exception.DetailNotFoundException;
import com.project.findPg.Exception.EmptyInputFieldException;
import com.project.findPg.Exception.EmptyListException;
import com.project.findPg.Exception.WrongCredentialsException;
import com.project.findPg.Repositories.UserRepository;
import com.project.findPg.services.UserServiceImpl;
import com.project.fingPg.responseAndRequest.UserRequest;

class UserTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        // Arrange
        User user = new User();
        user.setUserName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");

        User createdUser = new User();
        createdUser.setUserId(1);
        createdUser.setUserName("John");
        createdUser.setEmail("john@example.com");
        createdUser.setPassword("password");

        when(userRepository.save(any(User.class))).thenReturn(createdUser);

        // Act
        User result = userService.addUser(user);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals("John", result.getUserName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("password", result.getPassword());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());

        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.getUsers();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUsersThrowEmptyListException() {
        // Arrange
        List<User> users = new ArrayList<>();

        when(userRepository.findAll()).thenReturn(users);

        // Act & Assert
        assertThrows(EmptyListException.class, () -> userService.getUsers());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUser() {
        // Arrange
        int userId = 1;
        User user = new User();
        user.setUserId(userId);
        user.setUserName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUser(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals("John", result.getUserName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("password", result.getPassword());

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testGetUserThrowDetailNotFoundException() {
        // Arrange
        int userId = 1;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DetailNotFoundException.class, () -> userService.getUser(userId));

        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testDeleteUser() {
        // Arrange
        int userId = 1;
        User user = new User();
        user.setUserId(userId);
        user.setUserName("John");
        user.setEmail("john@example.com");
        user.setPassword("password");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).delete(user);
    }


    @Test
    void testUpdateUser() {
        // Arrange
        int userId = 1;
        User existingUser = new User();
        existingUser.setUserId(userId);
        existingUser.setUserName("John");
        existingUser.setEmail("john@example.com");
        existingUser.setPassword("password");

        User updatedUser = new User();
        updatedUser.setUserId(userId);
        updatedUser.setUserName("Updated John");
        updatedUser.setEmail("updatedjohn@example.com");
        updatedUser.setPassword("updatedpassword");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Act
        User result = userService.updateUser(updatedUser, userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals("Updated John", result.getUserName());
        assertEquals("updatedjohn@example.com", result.getEmail());
        assertEquals("updatedpassword", result.getPassword());

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }

  
    @Test
    void testFindUserByEmail() {
        // Arrange
        String email = "john@example.com";
        User user = new User();
        user.setUserId(1);
        user.setUserName("John");
        user.setEmail(email);
        user.setPassword("password");

        when(userRepository.findUserByEmail(email)).thenReturn(user);

        // Act
        User result = userService.findUserByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals("John", result.getUserName());
        assertEquals(email, result.getEmail());
        assertEquals("password", result.getPassword());

        verify(userRepository, times(1)).findUserByEmail(email);
    }

   
 /*   @Test
    void testFindUserByEmailAndPassword() {
        // Arrange
        String email = "john@example.com";
        String password = "password";
        User user = new User();
        user.setUserId(1);
        user.setUserName("John");
        user.setEmail(email);
        user.setPassword(password);

        when(userRepository.findUserByEmailAndPassword(email, password)).thenReturn(user);

        // Act
     //   User result = userService.findUserByEmailAndPassword(UserRequest userRequest);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals("John", result.getUserName());
        assertEquals(email, result.getEmail());
        assertEquals(password, result.getPassword());

        verify(userRepository, times(1)).findUserByEmailAndPassword(email, password);
    }

*/
    @Test
    void testFindUserByRole() {
        // Arrange
        String role = "admin";
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());

        when(userRepository.findUserByRole(role)).thenReturn(users);

        // Act
        List<User> result = userService.findUserByRole(role);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());

        verify(userRepository, times(1)).findUserByRole(role);
    }

    
}
