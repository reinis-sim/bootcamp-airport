package com.example.demo.controllers;  
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.demo.models.User;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
import com.example.demo.services.ITestingAirportService;
import com.example.demo.services.IUserService;
import com.example.demo.services.impl.AdminServiceImpl;
import com.example.demo.services.impl.EmailServiceImpl;  


@Controller
public class AirportTestingController {                  
	

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
	
	@Autowired
	IUserService userService;
	
	@Autowired
	EmailServiceImpl myEmail;
	
	@Autowired
	AdminServiceImpl adminService;
	
	
	@GetMapping("/")
	String test(Model model) {
		if(!locationRepo.existsById(1)) {
			Location l1 = new Location("Riga", "Latvia");
			Location l2 = new Location("Berlin", "Germany");
			locationRepo.save(l1);
			locationRepo.save(l2);
			Airport a1 = new Airport("RIX",  l1);
			Airport a2 = new Airport("BXX",  l2);
			Airport a3 = new Airport("qqqq", l1);
			Airport a4 = new Airport("wwww", l1);
			airportRepo.save(a1);
			airportRepo.save(a2);
			airportRepo.save(a3);
			airportRepo.save(a4);
			ArrayList<Airport>air = new ArrayList<Airport>();
			air.add(a1);
			air.add(a3);
			ArrayList<Airport>air2 = new ArrayList<Airport>();
			air2.add(a2);
			air2.add(a4);
		    ArrayList<Airport> permAir = new ArrayList<Airport>();
	        permAir.add(a1);
	        permAir.add(a2);
			Flight f1 = new Flight(9.00f, 2.00f,300f, permAir, 150);
			Flight f2 = new Flight(12.00f, 1.40f,250f, air, 100);
			Flight f3 = new Flight(20.00f, 5.20f,320f, air2, 300);
			flightRepo.save(f1);
			flightRepo.save(f2);
			flightRepo.save(f3);
			
			User u1 = new User("name1","surname1","123@gmail", "parole123", "ROLE_ADMIN", true);
			User u2 = new User("name2","surname2","abc@gmail", "paro321", "ROLE_USER", true);
			User u3 = new User("MYAdmin","surname","admin@email.com",BCrypt.hashpw("password", BCrypt.gensalt()),"ROLE_ADMIN",true);
			userRepo.save(u1);
			userRepo.save(u2);
			userRepo.save(u3);
			BoardingPass bo1 = new BoardingPass(f1, u1, 23);
			BoardingPass bo2 = new BoardingPass(f1, u2, 25);
			boardingPassRepo.save(bo1);
			boardingPassRepo.save(bo2);
			
			Luggage lug1 = new Luggage(12.99f, 5.75f, bo1);
			luggageRepo.save(lug1);
			
			userService.register("MYUser","surname","user@email.com","password");
			
			//flightRepo.save(new Flight(0f, 10f, airports, maxNumberOfPassangers))
			//System.out.println(userService.authenticate("email@email.com", "passsss"));
			//System.out.println(userService.authenticate("email@email.com", "passs"));
			
			//System.out.println(userService.bookFlight(userRepo.findByEmail("user@email.com"), f1, new ArrayList<Luggage>()));
			//System.out.println(userService.bookFlight(userRepo.findByEmail("user@email.com"), f2, new ArrayList<Luggage>()));
			//System.out.println("All Flights");
			//System.out.println(userService.selectAllFlights());
			//System.out.println("All flights in airport");
			//System.out.println(userService.selectAllFlightsInAirport(a2));
			//System.out.println("Booked flights by user");
			//System.out.println(userService.selectBookedFlightsByUser(userRepo.findByEmail("email@email.com")));
			//System.out.println("One booked flight by boardingpass");
			//System.out.println(userService.selectOneBookedFlightByUser(boardingPassRepo.findById(14).get()));
			//System.out.println("Saving PDF");
			
			//myEmail.sendEmail("email@email.lv",bo1 );
			
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String email = authentication.getName();
		    model.addAttribute("userObject",userService.selectOneUserByEmail(email));
		}
		
			System.out.println("------");
			
		return "home";

	}
}