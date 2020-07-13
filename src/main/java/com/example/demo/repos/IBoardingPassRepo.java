package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.User;

public interface IBoardingPassRepo extends CrudRepository<BoardingPass, Integer> {
	boolean existsByUserAndFlight(User user, Flight flight);
}
