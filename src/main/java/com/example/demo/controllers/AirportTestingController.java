package com.example.demo.controllers;  
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;  
import com.example.demo.services.ITestingAirportService;  

@Controller 

public class AirportTestingController {                  
	@Autowired         ITestingAirportService testService;          
	@GetMapping("/test")         
	public String getTest()         {                 
		testService.testModelsLayer();                 
		return "hello-page";         } 
	} 
