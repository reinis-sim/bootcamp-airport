package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.demo.models.Flight;
import com.example.demo.models.User;
import com.example.demo.repos.IFlightRepo;
import com.example.demo.repos.IUserRepo;
import com.example.demo.services.IAdminService;

public class AdminServiceImpl implements IAdminService {
	@Autowired
	IFlightRepo flightRepo;
	@Autowired
	IUserRepo userRepo;
	
	@Override
	public boolean insertFlight(Flight flight) {
		//TODO possibly more verifications
		if(flight.getAirports()!= null) {
			flightRepo.save(flight);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateFlightById(int id, Flight flight) {
		if(!flightRepo.existsById(id)) {
			return false;
		}else {
			Flight tempFlight = flightRepo.findById(id).get();
			tempFlight.setAirports(flight.getAirports());
			tempFlight.setBoardingPasses(flight.getBoardingPasses());
			tempFlight.setDuration(flight.getDuration());
			tempFlight.setMaxNumberOfPassangers(flight.getMaxNumberOfPassangers());
			tempFlight.setTime(flight.getTime());
			flightRepo.save(tempFlight);
			return true;
		}
	}

	@Override
	public boolean deleteFlightById(int id) {
		if(flightRepo.existsById(id)) {
			flightRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserById(int id, User user) {
		if(!userRepo.existsById(id)) {
			return false;
		}else {
			User tempUser = userRepo.findById(id).get();
			tempUser.setEmail(user.getEmail());
			tempUser.setEnabled(user.isEnabled());
			tempUser.setName(user.getName());
			tempUser.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
			tempUser.setRoles(user.getRoles());
			tempUser.setSurname(user.getSurname());
			userRepo.save(tempUser);
			return true;
		}
		
	}

	@Override
	public boolean deleteUserById(int id) {
		if(userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
}
