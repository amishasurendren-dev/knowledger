package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;


@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository ur;

	
	
	
	@Override
	public String addUser(Users user) {
		ur.save(user);
		return "User added successfully!";		
	}

	@Override
	public boolean checkEmail(String email) {
		return ur.existsByEmail(email);
	}


	@Override
	public boolean validate(String email, String password) {
		if(ur.existsByEmail(email)) {			
			Users u=ur.getByEmail(email);
			String dbPassword=u.getPassword();
			if(password.equals(dbPassword)) {
			return true;
			}

			else {
			return false;
			}

		}

		else{
              return false;
		}
	}
	
	@Override
	public String getUserRole(String email) 
	{
    Users u=ur.getByEmail(email);
    return u.getRole();
	}

	@Override
	public Users getUser(String email) 
	{
	return ur.getByEmail(email);
	}

	@Override
	public String updateUser(Users user) {
	ur.save(user);
	return "student updated successfully!";

	}



}
