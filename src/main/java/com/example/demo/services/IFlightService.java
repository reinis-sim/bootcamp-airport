package com.example.demo.services;

import java.util.ArrayList;


import com.example.demo.models.BoardingPass;

import com.example.demo.models.Airport;

import com.example.demo.models.Flight;

public interface IFlightService {
	

	
	//INSERT
	boolean addFlightsByObject(Flight flight);
	
	//UPDATE
	boolean updateFlightObjectById(int id, Flight flight, Airport airportFrom, Airport airportTo);
	
	//SELECT
	ArrayList<Flight> showAllFlights();
	Flight selectOneFlightById(int id) throws Exception;
	Flight selectOneFlightByBoardingPass(BoardingPass boardingPass) throws Exception;
	
	//DELETE
	boolean deleteFlightById(int id);

	
	
	

}
