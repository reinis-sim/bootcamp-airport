package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.User;
import com.example.demo.repos.IUserRepo;
import com.example.demo.services.IUserService;

public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepo userRepo;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	public boolean register(String name, String surname, String email, String password) {
		if(!userRepo.existsByEmail(email)) {
			userRepo.save(new User(name,surname,email,BCrypt.hashpw(password, BCrypt.gensalt()),"ROLE_USER",true));
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean authenticate(String email, String password) {
		if(!userRepo.existsByEmail(email)) {
			if(BCrypt.checkpw(password, userRepo.findByEmail(email).getPassword())) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean bookFlight(User user, Flight flight) {
		
		return false;
	}

	@Override
	public ArrayList<Flight> selectAllFlights() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Flight> selectBookedFlightsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exportBookedFlightAsPDF(BoardingPass boardingPass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Flight selectOneBookedFlightByUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
