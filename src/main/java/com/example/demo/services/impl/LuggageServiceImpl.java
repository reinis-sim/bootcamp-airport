package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Luggage;
import com.example.demo.repos.IBoardingPassRepo;
import com.example.demo.repos.ILuggageRepo;
import com.example.demo.services.ILuggageService;

@Service
public class LuggageServiceImpl implements ILuggageService {
	@Autowired
	ILuggageRepo lugRepo;
	@Autowired
	IBoardingPassRepo bpRepo;

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
	public ArrayList<Luggage> selectAllLuggageByBoardingPass(int id)throws Exception {
		if(id > 0)
		{
			if(lugRepo.existsByBoardingPassIDBPass(id)) {
			ArrayList<Luggage>bpLug = lugRepo.findByBoardingPassIDBPass(id);
			return bpLug;
			}
		}
		throw new Exception("Id is not correct and there is not customer with that id in System");
	}

	@Override
	public boolean insertNewLuggage(float price, float weight, BoardingPass boardingPass) {
		Luggage luggage = new Luggage(price, weight, boardingPass);
		lugRepo.save(luggage);
		return true;
	}
	@Override
	public boolean insertNewLuggage(float price, float weight, int boardingPass_id) {
		if(bpRepo.existsById(boardingPass_id)) {
			lugRepo.save(new Luggage(price,weight,bpRepo.findById(boardingPass_id).get()));
			return true;
		}
		return false;
	}
	@Override
	public boolean insertNewLuggageByObject(Luggage luggage) {
		Luggage lug = new Luggage(luggage.getPrice(), luggage.getWeight(), luggage.getBoardingPass());
		lugRepo.save(lug);
		return true;
	}

	@Override
	public boolean updateLuggage(int id, Luggage luggage) {
		if(!lugRepo.existsById(id)) {
			return false;
		}
		Luggage updateLug = lugRepo.findById(id).get();
		updateLug.setPrice(luggage.getPrice());
		updateLug.setWeight(luggage.getWeight());
		lugRepo.save(updateLug);
		return true;
	}

	@Override
	public boolean deleteLuggage(int id) {
		if(lugRepo.existsById(id)) {
			lugRepo.deleteById(id);
			return true;
		}
		return false;
	}
	@Override
	public boolean deleteLuggageByBoardingPass(int id) {
		if(lugRepo.existsByBoardingPassIDBPass(id)) {
			lugRepo.removeByBoardingPassIDBPass(id);
			return true;
		}
		return false;
	}
	

}
