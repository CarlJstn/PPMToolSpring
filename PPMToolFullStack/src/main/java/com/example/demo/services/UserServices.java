package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.User;
import com.example.demo.exceptions.ProjectIdException;
import com.example.demo.repositories.UserRepository; 

@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	//	Create and Update 
	public User saveOrUpdateUser(User user){
		try{
			String Password = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(Password);
	        return userRepository.save(user);
        }catch (Exception e){
            throw new ProjectIdException("Username "+user.getUsername()+" already exists");
        }
	}	
	// Get all users
	public List<User> findAllUsers(){
		return (List<User>) userRepository.findAll();
	}	
}
