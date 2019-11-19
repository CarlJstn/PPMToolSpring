package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import com.example.demo.exceptions.ProjectIdException;
import com.example.demo.repositories.UserRepository;


@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	//	Create and Update 
	public User saveOrUpdateUser(User user){
		if(user.getPassword().equals(user.getPasswordConfirm())) {
			try{
				user.setUsername(user.getUsername());
		        return userRepository.save(user);
	        }catch (Exception e){
	            throw new ProjectIdException("Username '"+user.getUsername()+"' already exists");
	        }
		} else {
			throw new ProjectIdException("Password does not match");
		}
	}
	
	// Get all users
	public Iterable<User> findAllUsers(){
		return userRepository.findAll();
	}
	

}
