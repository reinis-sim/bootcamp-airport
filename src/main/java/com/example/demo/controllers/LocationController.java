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

import com.example.demo.models.Location;
import com.example.demo.services.ILocationService;

@Controller
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	ILocationService locationService;
	
	@GetMapping("/showAll") // url - localhost:8080/location/showAll
	public String getShowAllLocations(Model model) {
		
		model.addAttribute("innerObject", locationService.showAllLocations());
		return "show-all-locations-page";// show-all-locations-page.html
	}
	
	@GetMapping ("/insert") // url - localhost:8080/location/insert
	public String getInsertLocation(Location location)
	{
		return "insert-location-page";//insert-location-page.html
	}
	
	
	@PostMapping("/insert")
	public String postInsertLocation(@Valid Location location, BindingResult result)
	{
		System.out.println(location);
		
		if(result.hasErrors())
		{
			return "insert-location-page";
		}
				
		locationService.addLocationByObject(location);
		return "redirect:/location/showAll";
		
	}
	
	
	@GetMapping("/showAll/{id}")//url address->localhost:8080/location/showAll/1
	public String getShowAllLocationsId(@PathVariable(name = "id") int id, Model model) {
		try
		{
			model.addAttribute("innerObject", locationService.selectOneLocationById(id));
			return "show-one-location-page";// show-one-location-page.html
			
		}
		catch (Exception e) {
			return "error";
		}
	}
	
	
	

	
	
	
	//CAN DELETE ONLY WHEN LOCATION NOT USED
	@GetMapping("/delete/{id}")
	public String getDeleteLocationById(@PathVariable(name = "id") int id, Model model)
	{
		try
		{
			locationService.deleteLocationById(id);
			
			model.addAttribute("innerObject", locationService.showAllLocations());
			return "show-all-locations-page";// show-all-locations-page.html
		}
		catch (Exception e) {
			return "error";
		}
	}
	
	

}
