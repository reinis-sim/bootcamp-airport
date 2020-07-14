package com.example.demo.controllers;  
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.models.User;
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.Airport;
import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Location;
import com.example.demo.models.Luggage;
import com.example.demo.repos.IAirportRepo;
import com.example.demo.repos.IBoardingPassRepo;
import com.example.demo.repos.IFlightRepo;
import com.example.demo.repos.ILocationRepo;
import com.example.demo.repos.ILuggageRepo;
import com.example.demo.repos.IUserRepo;
import com.example.demo.services.ITestingAirportService;
import com.example.demo.services.IUserService;
import com.example.demo.services.impl.EmailServiceImpl;  


@Controller 

public class AirportTestingController {                  
	@Autowired         ITestingAirportService testService;          
	@GetMapping("/test")         
	public String getTest()         {                 
		testService.testModelsLayer();                 
		return "hello-page";         } 

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
	
	
}
