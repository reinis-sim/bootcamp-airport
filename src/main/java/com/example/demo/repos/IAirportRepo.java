package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Airport;
import com.example.demo.models.Location;

public interface IAirportRepo extends CrudRepository<Airport, Integer> {

	boolean existsByTitleAndLocation(String title, Location location);

	boolean existsByTitleAndLocationCity(String title, String city);

}
