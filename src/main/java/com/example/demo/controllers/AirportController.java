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
import com.example.demo.models.Location;
import com.example.demo.repos.IAirportRepo;
import com.example.demo.services.IAirportService;

@Controller
@RequestMapping("/airport")
public class AirportController {

	@Autowired
	IAirportService airportService;
	
	
	@GetMapping("/showAll") // url - localhost:8080/airport/showAll
	public String getShowAllAirports(Model model) {
		
		model.addAttribute("innerObject", airportService.showAllAirports());
		return "show-all-airports-page";// show-all-airports-page.html

	}
	
	
	@GetMapping ("/insert") // url - localhost:8080/airport/insert
	public String getInsertAirport(Airport airport)
	{
		return "insert-airport-page";//insert-airport-page.html
	}
	
	
	@PostMapping("/insert")
	public String postInsertAirport(@Valid Airport airport, BindingResult result)
	{
		System.out.println(airport);
		
		if(result.hasErrors())
		{
			return "insert-airport-page";
		}
				
		airportService.addAirportByObject(airport);
		return "redirect:/airport/showAll";
		
	}
	
	@GetMapping("/showAll/{id}")//url address->localhost:8080/airport/showAll/1
	public String getShowAllAirportsId(@PathVariable(name = "id") int id, Model model) {
		try
		{
			model.addAttribute("innerObject", airportService.selectOneAirportById(id));
			return "show-one-airport-page";// show-one-airport-page.html
			
		}
		catch (Exception e) {
			return "error";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String getDeleteAirportById(@PathVariable(name = "id") int id, Model model)
	{
		try
		{
			airportService.deleteAirportById(id);
			
			model.addAttribute("innerObject", airportService.showAllAirports());
			return "show-all-airports-page";// show-all-airports-page.html
		}
		catch (Exception e) {
			return "error";
		}
	}
	
	
	

}
