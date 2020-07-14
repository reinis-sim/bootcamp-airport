package com.example.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Airport;
import com.example.demo.models.Flight;

public interface IAirportRepo extends CrudRepository<Airport, Integer> {
	
}
