package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Airport;
import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Location;
import com.example.demo.models.Luggage;
import com.example.demo.models.User;
import com.example.demo.repos.IAirportRepo;
import com.example.demo.repos.IBoardingPassRepo;
import com.example.demo.repos.IFlightRepo;
import com.example.demo.repos.ILocationRepo;
import com.example.demo.repos.ILuggageRepo;
import com.example.demo.repos.IUserRepo;

@Service
public class TestingAirportServiceImpl {
	
	@Autowired
	IAirportRepo airportRepo;
	
	@Autowired
	IBoardingPassRepo boardingPassRepo;
	
	@Autowired
	IFlightRepo flightRepo;
	
	@Autowired
	ILocationRepo locationRepo;
	
	@Autowired
	ILuggageRepo luggageRepo;
	
	@Autowired
	IUserRepo userRepo;
	
	
	public void testModelsLayer() {
		
		Location l1 = new Location("Riga", "Latvia");
		Location l2 = new Location("Berlin", "Germany");
		locationRepo.save(l1);
		locationRepo.save(l2);
		
		Airport a1 = new Airport("RIX",  l1);
		Airport a2 = new Airport("BX",  l2);
		airportRepo.save(a1);
		airportRepo.save(a2);
		
		
	    ArrayList<Airport> permAir = new ArrayList<Airport>();
        permAir.add(a1);
        permAir.add(a2);
        
        
		
		Flight f1 = new Flight(12.00f, 2.00f, permAir, 200);
		flightRepo.save(f1);
		
		User u1 = new User("name1","surname1","123@gmail", "parole123", "ROLE_ADMIN", true);
		User u2 = new User("name2","surname2","abc@gmail", "paro321", "ROLE_USER", true);

		userRepo.save(u1);
		userRepo.save(u2);
		
		BoardingPass bo1 = new BoardingPass(f1, u1, 23);
		BoardingPass bo2 = new BoardingPass(f1, u2, 25);
		boardingPassRepo.save(bo1);
		boardingPassRepo.save(bo2);
		
		Luggage lug1 = new Luggage(12.99f, 5.75f, bo1);
		luggageRepo.save(lug1);
		
		
		
		
		
		
		
		
	}

}
