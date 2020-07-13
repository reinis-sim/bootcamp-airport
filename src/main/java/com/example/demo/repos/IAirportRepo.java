package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Airport;

public interface IAirportRepo extends CrudRepository<Airport, Integer> {

}
