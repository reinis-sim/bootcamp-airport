package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;

public interface IFlightRepo extends CrudRepository<Flight,  Integer> {

	

	

	

}
