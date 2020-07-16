package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Location;
import com.example.demo.models.Luggage;
import com.example.demo.repos.IBoardingPassRepo;
import com.example.demo.repos.IFlightRepo;
import com.example.demo.repos.ILuggageRepo;
import com.example.demo.services.IFlightService;

@Service
public class FlightServiceImpl implements IFlightService {
	
	@Autowired
	IFlightRepo flightRepo;
	@Autowired
	IBoardingPassRepo bpRepo;
	@Autowired
	ILuggageRepo luggRepo;


	@Override
	public boolean addFlightsByObject(Flight flight) {
		try {
			flightRepo.save(flight);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public ArrayList<Flight> showAllFlights() {

		return (ArrayList<Flight>) flightRepo.findAll();
	}

	@Override
	public Flight selectOneFlightById(int id) throws Exception {
		if(id > 0)
		{
			
			if (flightRepo.existsById(id))
			{
				return flightRepo.findById(id).get();
			}
		}
		
		throw new Exception("Id is not correct and there is not a location with that id in System");
	}

	@Override
	public boolean deleteFlightById(int id) {

		if(id>0)
		{
			if(flightRepo.existsById(id))
			{
				Flight flight = flightRepo.findById(id).get();
				for(BoardingPass tempBP : flight.getBoardingPasses()) {
					for(Luggage tempLugg : tempBP.getAllLuggage()) {
						luggRepo.delete(tempLugg);
					}
					bpRepo.delete(tempBP);
				}
				flightRepo.delete(flight);
				return true;
				
			}
		}
		return false;
	}

	@Override
	public Flight selectOneFlightByBoardingPass(BoardingPass boardingPass) throws Exception {
		for(Flight f: flightRepo.findAll()) {
			if(f.getBoardingPasses().contains(boardingPass)) {
				return f;
			}
		}
		return null;
	}


}
