package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Luggage;
import com.example.demo.repos.ILuggageRepo;
import com.example.demo.services.ILuggageService;

@Service
public class LuggageServiceImpl implements ILuggageService {
	@Autowired
	ILuggageRepo lugRepo;

	@Override
	public Luggage selectOneLuggageById(int id) throws Exception {
		if(id > 0)
		{
			if(lugRepo.existsById(id)) {
			return lugRepo.findById(id).get();	
			}
		}
		throw new Exception("Id is not correct or there is no such Luggage with that id in System");
	}

	@Override
	public ArrayList<Luggage> selectAllLuggage() {
		return (ArrayList<Luggage>) lugRepo.findAll();
	}

	@Override
	public ArrayList<Luggage> selectAllLuggageByBoardingPass(BoardingPass boardingPass) {
		ArrayList<Luggage> luggageByBPass = lugRepo.findByBoardingPass(boardingPass);
		if(luggageByBPass!=null)
			return luggageByBPass;
		
		return new ArrayList<>();
	}

	@Override
	public boolean insertNewLuggage(float price, float weight, BoardingPass boardingPass) {
		Luggage luggage = new Luggage(price, weight, boardingPass);
		lugRepo.save(luggage);
		return true;
	}

	@Override
	public boolean insertNewLuggageByObject(Luggage luggage) {
		Luggage lug = new Luggage(luggage.getPrice(), luggage.getWeight(), luggage.getBoardingPass());
		lugRepo.save(lug);
		return true;
	}

}
