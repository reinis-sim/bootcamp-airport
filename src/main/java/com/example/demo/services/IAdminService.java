package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.models.Flight;
import com.example.demo.models.User;

public interface IAdminService {
	boolean insertFlight(Flight flight);
	boolean updateFlightById(int id ,Flight flight);
	boolean deleteFlightById(int id);
	ArrayList<Flight>getAllFlightsByAirportID(int id);
	//boolean insertUser();
	boolean updateUserById(int id, User user);
	boolean deleteUserById(int id);
}
