package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collection;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Luggage;
import com.example.demo.models.User;

public interface IBoardingPassService {

	
	//select
	BoardingPass selectOneBoardingPassById(int id) throws Exception;
	ArrayList<BoardingPass> selectAllBoardingPasses();
	ArrayList<BoardingPass> selectAllBoardingPassesByFlight(Flight flight);
	
	//create
	boolean insertNewBoardingPass(Flight flight, User user, int seat, Collection<Luggage> allLuggage);
	boolean insertNewBoardingPassByObject(BoardingPass boardingPass);
	
	//save testing data
	void saveTestingData();
	
	
}
