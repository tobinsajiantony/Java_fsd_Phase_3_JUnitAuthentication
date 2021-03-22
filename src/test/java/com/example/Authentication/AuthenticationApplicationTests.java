package com.example.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Authentication.entities.User;
import com.example.Authentication.repositories.UserRepository;
import com.example.Authentication.services.UserService;

@SpringBootTest
class AuthenticationApplicationTests {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	

	@Test
	void contextLoads() {
	}
	
	   @Test
	    public void AuthenticateUser() {
	    	User dummyUser = new User();
	        dummyUser.setName("Dummy");
	        dummyUser.setEmail("test@test.com");
	        dummyUser.setPassword("password");
	        userRepository.save(dummyUser);
	        
	        assertTrue(userService.authenticateUser(dummyUser.getName(), dummyUser.getPassword()).isValid());
	        User deleteUser = userRepository.findByName("Dummy");
	        userRepository.delete(deleteUser);
	    }
	   
	   @Test
	    public void CreateUser() {
	    	User dummyUser = new User();
	        dummyUser.setName("Dummy");
	        dummyUser.setEmail("test@test.com");
	        dummyUser.setPassword("password");
	        
	        assertTrue(userService.createUser(dummyUser.getName(), dummyUser.getPassword(), dummyUser.getPassword(), dummyUser.getEmail()).isValid());
	        User deleteUser = userRepository.findByName("Dummy");
	        userRepository.delete(deleteUser);
	    }
	   
	   @Test
	    public void GetUserByName() {
	    	User dummyUser = new User();
	        dummyUser.setName("Dummy");
	        dummyUser.setEmail("test@test.com");
	        dummyUser.setPassword("password");
	        userService.createUser(dummyUser.getName(), dummyUser.getPassword(), dummyUser.getPassword(), dummyUser.getEmail());
	        assertEquals(dummyUser.getName(), userService.GetUserByName("Dummy").getName());
	        User deleteUser = userRepository.findByName("Dummy");
	        userRepository.delete(deleteUser);
	    }

}
