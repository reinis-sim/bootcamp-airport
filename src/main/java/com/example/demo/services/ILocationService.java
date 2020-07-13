package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.models.Location;


public interface ILocationService {
	
	
	//INSERT
	boolean addLocation(String city, String country);
	
	//SELECT
	ArrayList<Location> showAllLocations();
	
	//UPDATE
	boolean updateLocationById(int id, String city, String country);
	boolean updateLocationObjectById(int id, Location location);
	
	

}
