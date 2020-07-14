package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.IBoardingPassService;
import com.example.demo.services.ILuggageService;

@Controller
@RequestMapping("/boardingPass")


public class BoardingPassController {

	@Autowired
	IBoardingPassService bpService;
	@Autowired
	ILuggageService lugService;
	
	@GetMapping("/showAllBoardingPass") // url address->localhost:8080/boardingPass/showAllBoardingPass
	public String getShowAllBoardingPass(Model model) {
		
		model.addAttribute("innerObject", bpService.selectAllBoardingPasses());
		return "show-all-boarding-pass";
	}
	@GetMapping("/showOneBoardingPass/{id}")  
	public String getShowAllBoardingPassId(@PathVariable(name = "id") int id, Model model) {
		try
		{
			model.addAttribute("innerObject", bpService.selectOneBoardingPassById(id));
			return "show-one-boarding-pass";
		}
		catch (Exception e) {
			return "error";
		}
	}
	@GetMapping("/showFlightBoardingPass/{id}")  
	public String getShowFlightBoardingPassId(@PathVariable(name = "id") int id, Model model) {
		try
		{
			model.addAttribute("innerObject", bpService.selectAllBoardingPassesByFlightID(id));
			return "show-all-boarding-pass";
		}
		catch (Exception e) {
			return "error";
		}
	}
}
