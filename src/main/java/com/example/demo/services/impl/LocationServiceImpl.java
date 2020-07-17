package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Airport;
import com.example.demo.models.Location;
import com.example.demo.repos.IAirportRepo;
import com.example.demo.repos.ILocationRepo;
import com.example.demo.services.IAirportService;
import com.example.demo.services.ILocationService;

@Service
public class LocationServiceImpl implements ILocationService {

	@Autowired
	ILocationRepo locationRepo;
	@Autowired
	IAirportRepo airportRepo;
	@Autowired
	IAirportService airportService;
	
	@Override
	public boolean addLocation(String city, String country)	{
	
	if(locationRepo.existsByCityAndCountry(city, country))
	{
		return false;
	}
	
	
	Location loc = new Location(city, country);
	locationRepo.save(loc);
	return true;
	
}

	@Override
	public ArrayList<Location> showAllLocations() {

		return (ArrayList<Location>) locationRepo.findAll();
	}


	
	@Override
	public boolean updateLocationById(int id, String city, String country){

		
		if(id>0)
		{
			
			if(locationRepo.existsById(id))
			{
				Location locToUpdate = locationRepo.findById(id).get();
				locToUpdate.setCity(city);
				locToUpdate.setCountry(country);
				//TODO set also new type
				locationRepo.save(locToUpdate);
				return true;
			}
			
		}
		return false;
	}

	@Override
	public boolean updateLocationObjectById(int id, Location location) {
		if(id>0)
		{
			
			if(locationRepo.existsById(id))
			{
				Location locToUpdate = locationRepo.findById(id).get();
				locToUpdate.setCity(location.getCity());
				locToUpdate.setCountry(location.getCountry());
				//TODO set also new type
				locationRepo.save(locToUpdate);
				return true;
			}
			
		}
		return false;
	}


	@Override
	public boolean deleteLocationById(int id) {

		if(id>0)
		{
			if(locationRepo.existsById(id))
			{
				;
				for(Airport airport : locationRepo.findById(id).get().getAirport()) {
					airportService.deleteAirportById(airport.getIDAirport());
				}
				locationRepo.deleteById(id);
				return true;
				
			}
		}
		return false;
	}

	@Override
	public boolean addLocationByObject(Location location) {

		
		
		if(locationRepo.existsByCityAndCountry(location.getCity(), location.getCountry()))
		{
			return false;
		}
		
		Location loc = new Location(location.getCity(), location.getCountry());
		locationRepo.save(loc);
		return true;
		
		
	}

	@Override
	public Location selectOneLocationById(int id) throws Exception {
		if(id > 0)
		{
			
			if (locationRepo.existsById(id))
			{
				return locationRepo.findById(id).get();
			}
		}
		
		throw new Exception("Id is not correct and there is not a location with that id in System");
	}
	
}