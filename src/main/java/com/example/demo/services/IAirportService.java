package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.models.Airport;
import com.example.demo.models.Location;


public interface IAirportService {
	
	//INSERT
	boolean addAirportByObject(Airport airport);
	
	//SELECT
	ArrayList<Airport> showAllAirports();
	Airport selectOneAirportById(int id) throws Exception;
	Airport selectOneAirportByTitle(String title);
	
	//DELETE
	boolean deleteAirportById(int id);
	
	//UPDATE
	boolean updateAirportObjectById(int id, Airport airport);

}
