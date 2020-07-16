package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Luggage;
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
	@GetMapping("/{pass_id}/newLuggage")
	public String getAddNewLuggage(@PathVariable("pass_id") int pass_id, Luggage luggage) {
		return "create-luggage";
	}
	@PostMapping("/{pass_id}/newLuggage")
	public String postAddNewLuggage(@PathVariable("pass_id") int pass_id,
			@ModelAttribute @Valid Luggage luggage,
			BindingResult result) {
		if(result.hasErrors()) {
			return "create-luggage";
		}
		if(lugService.insertNewLuggage(luggage.getPrice(), luggage.getWeight(), pass_id )) {
			return "redirect:/";
		}
		return "create-luggage";
	}
	@GetMapping("/{pass_id}/{lugg_id}/update")
	public String getUpdateLuggage(@PathVariable("pass_id") int pass_id,
			@PathVariable("lugg_id") int lugg_id, 
			Model model) {
		try {
			model.addAttribute("luggage",lugService.selectOneLuggageById(lugg_id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "update-luggage";
	}
	@PostMapping("/{pass_id}/{lugg_id}/update")
	public String postUpdateLuggage(@PathVariable("pass_id") int pass_id,
			@PathVariable("lugg_id") int lugg_id,
			Luggage luggage, BindingResult result) {
		if(result.hasErrors()) {
			return "update-luggage";
		}
		try {
			lugService.updateLuggage(lugg_id, luggage);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "update-luggage";
		}
		return "redirect:/";
	}
	@GetMapping("/{pass_id}/{lugg_id}/delete")
	public String getDeleteLuggage(@PathVariable("pass_id") int pass_id,@PathVariable("lugg_id")int lugg_id) {
		try {
			lugService.deleteLuggage(lugg_id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/";
	}
	@GetMapping("/{pass_id}/email")
	public String postSendBoardingPassToEmail() {
		System.out.println("email page");
		return "hello-page";
	}
}
