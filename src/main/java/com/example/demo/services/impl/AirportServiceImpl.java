package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Airport;
import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Location;
import com.example.demo.models.Luggage;
import com.example.demo.repos.IAirportRepo;
import com.example.demo.repos.IBoardingPassRepo;
import com.example.demo.repos.IFlightRepo;
import com.example.demo.repos.ILocationRepo;
import com.example.demo.repos.ILuggageRepo;
import com.example.demo.services.IAirportService;

@Service
public class AirportServiceImpl implements IAirportService{
	
	@Autowired
	IAirportRepo airportRepo;
	
	@Autowired
	ILocationRepo locationRepo;
	
	@Autowired
	IFlightRepo flightRepo;
	
	@Autowired
	IBoardingPassRepo bpRepo;
	
	@Autowired
	ILuggageRepo luggRepo;

	@Override
	public boolean addAirportByObject(Airport airport) {
		if(airportRepo.existsByTitleAndLocationCity(airport.getTitle(), airport.getLocation().getCity()))
		{
			return false;
		}
		
		Location loc = new Location(airport.getLocation().getCity(), airport.getLocation().getCountry());
		System.out.println(airport.getLocation().getCity() + airport.getLocation().getCountry());
		locationRepo.save(loc);
		Location locFromDB = locationRepo.findByCityAndCountry(airport.getLocation().getCity(), airport.getLocation().getCountry());
		Airport air = new Airport(airport.getTitle(),airport.getFlights() ,locFromDB);
		airportRepo.save(air);
		return true;
	}

	@Override
	public ArrayList<Airport> showAllAirports() {

		return (ArrayList<Airport>) airportRepo.findAll();
	}

	@Override
	public Airport selectOneAirportById(int id) throws Exception {
		if(id > 0)
		{
			
			if (airportRepo.existsById(id))
			{
				return airportRepo.findById(id).get();
			}
		}
		
		throw new Exception("Id is not correct and there is not a location with that id in System");
	}

	@Override
	public boolean deleteAirportById(int id){
		if(id>0)
		{
			if(airportRepo.existsById(id))
			{
				Airport airport = airportRepo.findById(id).get();
				//Deletes All Airport Flights
				for(Flight flight : airport.getFlights()) {
					flight.setAirports(null);
					//Deletes All Flight BoardingPasses
					for(BoardingPass pass : flight.getBoardingPasses()) {
						//Deletes All BoardingPasses Luggages
						for(Luggage luggage : pass.getAllLuggage()) {
							luggRepo.delete(luggage);
						}
						bpRepo.delete(pass);
					}
					flightRepo.delete(flight);
				}
				airportRepo.delete(airport);
				return true;
				
			}
		}
		return false;
	}

	
	@Override
	public boolean updateAirportObjectById(int id, Airport airport) {
		if(id>0)
		{
			
			if(airportRepo.existsById(id))
			{
				Airport airportToUpdate = airportRepo.findById(id).get();
				airportToUpdate.setTitle(airport.getTitle());
				Location locToUpdate = locationRepo.findByCity(airportToUpdate.getLocation().getCity());
				locToUpdate.setCity(airport.getLocation().getCity());
				locationRepo.save(locToUpdate);
				airportToUpdate.setLocation(locToUpdate);
				
				
				airportRepo.save(airportToUpdate);
				return true;
			}
			
		}
		return false;
	}

	@Override
	public Airport selectOneAirportByTitle(String title) {
		try {
			return airportRepo.findByTitle(title);
		} catch (Exception e) {
			System.out.println(e);
			return new Airport();
		}
	}

}
