package com.example.demo.repos;

import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.models.Airport;
import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.User;

public interface IFlightRepo extends CrudRepository<Flight, Integer> {
	ArrayList<Flight> findAllByAirports(Airport airport);
	ArrayList<Flight> findAllByBoardingPassesUser(User user);
	Flight findByBoardingPasses(BoardingPass boardingPass);
	ArrayList<Flight> findAllByAirportsContains(Airport airport);

	

	

	

}
