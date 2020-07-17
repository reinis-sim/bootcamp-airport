package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Luggage;
import com.example.demo.models.User;

public interface ILuggageService {

	Luggage selectOneLuggageById(int id) throws Exception;
	ArrayList<Luggage> selectAllLuggage();
	ArrayList<Luggage> selectAllLuggageByBoardingPass(int id) throws Exception;
	ArrayList<Luggage> selectAllLuggageByUser(User user) throws Exception;
	
	//create
	boolean insertNewLuggage(float price, float weight, BoardingPass boardingPass);
	boolean insertNewLuggage(float price, float weight, int boardingPass_id);
	boolean insertNewLuggageByObject(Luggage luggage);
	
	//update
	boolean updateLuggage(int id, Luggage luggage);
	
	//delete 
	boolean deleteLuggage(int id);
	
	
}
