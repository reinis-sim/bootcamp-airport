package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import com.example.demo.models.Luggage;
import com.example.demo.repos.IAirportRepo;
import com.example.demo.repos.IBoardingPassRepo;
import com.example.demo.repos.IUserRepo;
import com.example.demo.services.IAirportService;
import com.example.demo.services.IFlightService;
import com.example.demo.services.IUserService;

@Controller
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	IFlightService flightService;
	@Autowired
	IAirportService airportService;
	@Autowired
	IUserService userService;
	@Autowired
	IUserRepo userRepo;
	@Autowired
	IBoardingPassRepo bpRepo;
	@GetMapping("/showAll") // url - localhost:8080/flight/showAll
	public String getShowAllFlights(Model model) {
		
		model.addAttribute("flights", flightService.showAllFlights());
		return "show-all-flights-page";// show-all-flights-page.html
	}
	
	
	@GetMapping("/showAll/{id}")//url address->localhost:8080/flight/showAll/1
	public String getShowAllFlightsId(@PathVariable(name = "id") int id, Model model) {
		try
		{
			model.addAttribute("flights", flightService.selectOneFlightById(id));
			return "show-one-flight-page";// show-one-flight-page.html
			
		}
		catch (Exception e) {
			return "error";
		}
	}
	
	@GetMapping ("/insert") // url - localhost:8080/flight/insert
	public String getInsertFlight(Airport airportFrom, Airport airportTo, Flight flight, Model model){
		model.addAttribute("airportList", airportService.showAllAirports());
		model.addAttribute("airportTo", airportFrom);
		model.addAttribute("airportFrom", airportTo);
		//model.addAttribute("airportTo", new Airport());
		return "insert-flight-page";//insert-flight-page.html
	}
	
	
	@PostMapping("/insert")
	public String postInsertFlight(@ModelAttribute("airportFrom")Airport airportFrom, 
			@ModelAttribute("airportTo") Airport airportTo,
			@Valid Flight flight, BindingResult result)
	{
		//HORRIBLE IMPLEMENTATION (C) ARTIS
		try {
			String[] titles = airportFrom.getTitle().split(",");
			Airport fromAirp = airportService.selectOneAirportByTitle(titles[0]);
			Airport toAirp = airportService.selectOneAirportByTitle(titles[1]);
			if(fromAirp.getTitle().equals(toAirp.getTitle())|| result.hasErrors()) {
				return "insert-flight-page";
			}
			ArrayList<Airport> airportList = new ArrayList<>();
			airportList.add(fromAirp);
			airportList.add(toAirp);
			flight.setAirports(airportList);
			flightService.addFlightsByObject(flight);
			return "redirect:/flight/showAll";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "insert-flight-page";
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteFlight(@PathVariable int id) {
		flightService.deleteFlightById(id);
		return "redirect:/flight/showAll";
	}
	
	@GetMapping("/{flight_id}/book")
	public String getBookFlight(@PathVariable int flight_id, Model model, Luggage luggage) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.isAuthenticated()) {
			return "error2";
		}
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        if(userService.authenticate(userDetail.getUsername(), userDetail.getPassword())) {
        	try {
				model.addAttribute("innerObject",flightService.selectOneFlightById(flight_id));
				return "book-flight";
        	} catch (Exception e) {
				System.out.println("Error booking a flight:");
				System.out.println(e.getMessage());
			}
        }
		return "error";
	}
	@PostMapping("/{flight_id}/book")
	public String postBookFlight(@PathVariable int flight_id, @Valid Luggage luggage) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        ArrayList<Luggage>allLuggage = new ArrayList<Luggage>();
        allLuggage.add(luggage);
        System.out.println(allLuggage);
        try {
			userService.bookFlight(userRepo.findByEmail(userDetail.getUsername()), flightService.selectOneFlightById(flight_id), allLuggage);
		} catch (Exception e) {
			System.out.println("Error booking a flight:");
			System.out.println(e.getMessage());
		}
		return "redirect:/";
	}
}
