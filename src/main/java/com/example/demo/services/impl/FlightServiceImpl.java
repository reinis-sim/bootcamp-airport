package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Flight;
import com.example.demo.models.Location;
import com.example.demo.repos.IFlightRepo;
import com.example.demo.services.IFlightService;

@Service
public class FlightServiceImpl implements IFlightService {
	
	@Autowired
	IFlightRepo flightRepo;


	@Override
	public boolean addFlightsByObject(Flight flight) {
		// TODO Auto-generated method stub
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
				flightRepo.deleteById(id);
				return true;
				
			}
		}
		return false;
	}


}
