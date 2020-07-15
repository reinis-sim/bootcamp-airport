package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Luggage;
import com.example.demo.services.IBoardingPassService;
import com.example.demo.services.ILuggageService;

@Controller
@RequestMapping("/luggage")
public class LuggageController {

	@Autowired
	IBoardingPassService bpService;
	@Autowired
	ILuggageService lugService;
	
	@GetMapping("/showAllLuggage") 
	public String getShowAllLuggage(Model model) {
		
		model.addAttribute("innerObject", lugService.selectAllLuggage());
		return "show-all-luggage";
	}
	
	@GetMapping("/showOneLuggage/{id}") 
	public String getShowAllLuggageId(@PathVariable(name = "id") int id, Model model) {
		try
		{
			model.addAttribute("innerObject", lugService.selectOneLuggageById(id));
			return "show-one-luggage";
		}
		catch (Exception e) {
			return "error";
		}
	}
	@GetMapping("/showLuggageBoardingPass/{id}")  
	public String getShowLuggageBoardingPass(@PathVariable(name = "id") int id, Model model) {
		try
		{
			model.addAttribute("innerObject", lugService.selectAllLuggageByBoardingPass(id));
			return "show-one-luggage";
		}
		catch (Exception e) {
			return "error";
		}
	}
}
