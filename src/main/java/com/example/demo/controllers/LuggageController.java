package com.example.demo.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Luggage;
import com.example.demo.models.User;
import com.example.demo.services.IBoardingPassService;
import com.example.demo.services.ILuggageService;
import com.example.demo.services.IUserService;

@Controller
@RequestMapping("/luggage")
public class LuggageController {

	@Autowired
	IBoardingPassService bpService;
	@Autowired
	ILuggageService lugService;
	@Autowired
	IUserService userService;
	
	@GetMapping("/showAllLuggage") 
	public String getShowAllLuggage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("ROLE_USER"))) {
        	 UserDetails userDetail = (UserDetails) auth.getPrincipal();
        	 if(userService.authenticate(userDetail.getUsername(), userDetail.getPassword())) {
        		 User user = userService.selectOneUserByEmail(userDetail.getUsername());
        		 try {
					model.addAttribute("innerObject",lugService.selectAllLuggageByUser(user));
					
				} catch (Exception e) {
					System.out.println("Error retrieving luggages");
					System.out.println(e.getMessage());
				}
             }
        }else {
        	model.addAttribute("innerObject", lugService.selectAllLuggage());
        }
		
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
	@GetMapping("/showOneLuggage/{id}/delete") 
	public String getDeleteLuggage(@PathVariable(name = "id") int id, Model model) {
		try
		{
			lugService.deleteLuggage(id);
			return "redirect:/luggage/showAllLuggage";
		}
		catch (Exception e) {
			return "error";
		}
	}
	@GetMapping("/showOneLuggage/{id}/update") 
	public String getUpdateLuggage(@PathVariable(name = "id") int id, Model model) {
		try {
			model.addAttribute("luggage",lugService.selectOneLuggageById(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "update-luggage";
	}
	@PostMapping("/showOneLuggage/{id}/update") 
	public String postUpdateLuggage(@PathVariable(name = "id") int id,@Valid Luggage luggage, BindingResult result) {
		if(result.hasErrors()) {
			return "update-luggage";
		}
		lugService.updateLuggage(id, luggage);
		return "redirect:/luggage/showAllLuggage";
	}
}
