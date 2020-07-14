package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.User;
import com.example.demo.repos.IFlightRepo;
import com.example.demo.repos.IUserRepo;
import com.example.demo.services.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	@Autowired
	IUserRepo userRepo;
	
	@GetMapping("/register")
	public String register(User user) {
		return "user-register";
	}
	@PostMapping("/register")
	public String register(@ModelAttribute User user, BindingResult result) {
		if(result.hasErrors()) {
			return "user-register";
		}
		userService.register(user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
		return "redirect:/";
	}
	
	//@GetMapping("/login")
	/*
	@GetMapping("/book")
	public String bookFlight() {
		return "";
	}
	*/
	@GetMapping("/bookings")
	public String selectBookedFligths(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        if(userService.authenticate(userDetail.getUsername(), userDetail.getPassword())) {
        	//TODO switch from using repo 
        	model.addAttribute("flights",userService.selectBookedFlightsByUser(userRepo.findByEmail(userDetail.getUsername())));
        }
		return "user-bookings";
	}
	@GetMapping("/allFlights")
	public String selectAllFlights(Model model) {
		//System.out.println(">>>>"+userService.selectAllFlights());
		model.addAttribute("flights",userService.selectAllFlights());
		//System.out.println(userService.selectAllFlights().get(0).getAirports());
		//model.addAttribute("",
		return "user-bookings";
	}
	@GetMapping("/booked")
	public String selectOneBookedFlightByUser(Model model) {
		//TODO
		//model.addAttribute("fligths",userService.selectOneBookedFlightByUser(boardingPass);
		return "error";
	}
	
	
}
