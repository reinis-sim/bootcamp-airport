package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Location;

public interface ILocationRepo extends CrudRepository<Location, Integer> {

	boolean existsByCityAndCountry(String city, String country);

	Location findByCityAndCountry(String string, String country);

	Location findByCity(String city);

}
