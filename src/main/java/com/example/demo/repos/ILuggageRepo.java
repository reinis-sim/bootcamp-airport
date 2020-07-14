package com.example.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Luggage;

public interface ILuggageRepo extends CrudRepository<Luggage, Integer> {


	

	boolean existsByBoardingPassIDBPass(int id);

	ArrayList<Luggage> findByBoardingPassIDBPass(int id);

	

	

	

}
