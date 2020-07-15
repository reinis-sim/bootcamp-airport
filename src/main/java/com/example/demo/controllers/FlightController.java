package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Airport;
import com.example.demo.models.Flight;
import com.example.demo.services.IFlightService;

@Controller
@RequestMapping("/flight")
public class FlightController {
	
	@Autowired
	IFlightService flightService;
	
	@GetMapping("/showAll") // url - localhost:8080/flight/showAll
	public String getShowAllFlights(Model model) {
		
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
	public String getInsertFlight(Flight flight)
	{
		return "insert-flight-page";//insert-flight-page.html
	}
	
	
	@PostMapping("/insert")
	public String postInsertFlight(@Valid Flight flight, BindingResult result)
	{
		System.out.println(flight);
		
		if(result.hasErrors())
		{
			return "insert-flight-page";
		}
				
		flightService.addFlightsByObject(flight);
		return "redirect:/flight/showAll";
		
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
}
