package com.example.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.User;

public interface IBoardingPassRepo extends CrudRepository<BoardingPass, Integer> {
	boolean existsByUserAndFlight(User user, Flight flight);
	boolean existsByFlight(Flight flight);

	ArrayList<BoardingPass> findByFlight(Flight flight);

	

	boolean existsByFlightIDFlight(int id);

	ArrayList<BoardingPass> findByFlightIDFlight(int id);
	ArrayList<BoardingPass> findAllByUser(User user);

}
