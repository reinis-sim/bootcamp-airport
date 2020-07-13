package com.example.demo.services;

import java.util.ArrayList;

import com.example.demo.models.Airport;
import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Luggage;
import com.example.demo.models.User;

public interface IUserService {
	boolean register(String name, String surname, String email, String password);
	boolean authenticate(String email, String password);
	boolean bookFlight(User user, Flight flight, ArrayList<Luggage>allLuggage);
	ArrayList<Flight>selectAllFlights();
	ArrayList<Flight>selectAllFlightsInAirport(Airport airport);
	ArrayList<Flight>selectBookedFlightsByUser(User user);
	boolean exportBookedFlightAsPDF(BoardingPass boardingPass);
	Flight selectOneBookedFlightByUser(BoardingPass boardingPass);
}
