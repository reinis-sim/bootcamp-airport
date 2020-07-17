package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.repos.IAirportRepo;
import com.example.demo.services.IAirportService;
import com.example.demo.services.IFlightService;

@Controller
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	IFlightService flightService;
	@Autowired
	IAirportService airportService;
	
	@GetMapping("/showAll") // url - localhost:8080/flight/showAll
	public String getShowAllFlights(Model model) {
		
		System.out.println("testing" + flightService.showAllFlights().get(0).getAirports().stream().findFirst().get().getTitle());
		model.addAttribute("flights", flightService.showAllFlights());
		return "show-all-flights-page";// show-all-flights-page.html
	}
	
	
	@GetMapping("/showAll/{id}")//url address->localhost:8080/flight/showAll/1
	public String getShowAllFlightsId(@PathVariable(name = "id") int id, Model model) {
		try
		{
			model.addAttribute("innerObject", flightService.selectOneFlightById(id));
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
	
	/*
	
	@GetMapping("/delete/{id}")
	public String getDeleteFlightById(@PathVariable(name = "id") int id, Model model)
	{
		try
		{
			flightService.deleteFlightById(id);
			
			model.addAttribute("innerObject", flightService.showAllFlights());
			return "show-all-flights-page";// show-all-flights-page.html
		}
		catch (Exception e) {
			return "error";
		}
	}
*/
	
	
	//TODO NOT WORKING
	//update functionality
	@GetMapping("/update/{id}")//url address->localhost:8080/airport/update/{id}
	public String getUpdateFlightById(@PathVariable(name = "id") int id, Model model, Airport airportFrom, Airport airportTo) {
	
		try
		{
			Flight flight = flightService.selectOneFlightById(id);
			
			System.out.println(flight);
			model.addAttribute("airportList", airportService.showAllAirports());
			model.addAttribute("flight", flight);
			model.addAttribute("airportTo", airportFrom);
			model.addAttribute("airportFrom", airportTo);
			return "update-one-flight-page";
		}
		catch (Exception e) {
			return "error";
		}
		}
	
	
	
	@PostMapping("/update/{id}")
	public String postUpdateFlightById(@PathVariable(name = "id") int id, Flight flight,@ModelAttribute("airportFrom")Airport airportFrom, 
			@ModelAttribute("airportTo") Airport airportTo)
	{

		System.out.println(airportFrom + " " + airportTo);
		System.out.println(flight);
		
		flightService.updateFlightObjectById(id, flight, airportFrom, airportTo);
		return "redirect:/flight/showAll";
	}
	
	
	
	
	
	
	
}
