package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Flight;

public interface IFlightRepo extends CrudRepository<Flight,  Integer> {

}
