package com.example.Authentication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Authentication.entities.User;
import com.example.Authentication.exceptions.UserNotFoundException;
import com.example.Authentication.models.Message;
import com.example.Authentication.repositories.UserRepository;



@Service
public class UserService {
	
	@Autowired
	 private UserRepository userRepository;
	
	

    public Iterable<User> GetAllUsers()
    {
        return userRepository.findAll();
    }


    public User GetUserByName(String name) {
        User foundUser = userRepository.findByName(name);
        return foundUser;
    }
    
    public User GetUserById(int id) {
    	User foundUser = userRepository.findById(id).orElse(null);
    	return foundUser;
    }
    
    public Message authenticateUser(String name, String password) {
    	if(name != "" && password != "") {
    	User user = userRepository.findByNameAndPassword(name, password);
        if(user != null)
        	return new Message(true, "Logged-In Successfully");
        else
        	return new Message(false, "Name or Password Not Valid!");
    	}
    	else if(name == "") {
    		return new Message(false, "Enter Name!");
    	}
    	else {
    		return new Message(false, "Enter Password!");
    	}
    }
    
    public Message createUser(String name, String password, String passwordre, String email) {
    	System.out.println(name);
    	System.out.println(password);
    	System.out.println(passwordre);
    	System.out.println(email);
    	if(name!="" && password!="" && passwordre!="" && email!="") 
    	{
    		if(password.equals(passwordre)) {
    			User user = new User(name, email, password);
    			userRepository.save(user);
    			return new Message(true, "User added Successfully");
    		}
    		else {
    			return new Message(false, "The Entered Passwords Do Not Match!");
    		}
    	}
    	else if (name == "")
    	{
    		return new Message(false, "Please Enter The Name!");
    	}
    	else if (email == "")
    	{
    		return new Message(false, "Please Enter The Email!");
    	}
    	else if (password == "")
    	{
    		return new Message(false, "Please Enter The Password!");
    	}
    	else
    	{
    		return new Message(false, "Please Re-Enter The Password!");
    	}
    	
    }


}
