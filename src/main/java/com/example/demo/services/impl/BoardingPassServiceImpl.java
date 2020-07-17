package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Luggage;
import com.example.demo.models.User;
import com.example.demo.repos.IBoardingPassRepo;
import com.example.demo.repos.IFlightRepo;
import com.example.demo.repos.ILuggageRepo;
import com.example.demo.repos.IUserRepo;
import com.example.demo.services.IBoardingPassService;

@Service
public class BoardingPassServiceImpl implements IBoardingPassService{
	
	@Autowired
	IBoardingPassRepo bpRepo;
	@Autowired
	IUserRepo userRepo;
	@Autowired
	IFlightRepo flRepo;
	@Autowired
	ILuggageRepo lugRepo;

	@Override
	public BoardingPass selectOneBoardingPassById(int id) throws Exception {
		if(id > 0)
		{
			if(bpRepo.existsById(id)) {
			return bpRepo.findById(id).get();	
			}
		}
		throw new Exception("Id is not correct or there is no such Boarding Pass with that id in System");
	}

	@Override
	public ArrayList<BoardingPass> selectAllBoardingPasses() {
		return (ArrayList<BoardingPass>) bpRepo.findAll();
	}

	@Override
	public boolean insertNewBoardingPass(Flight flight, User user, int seat, Collection<Luggage> allLuggage) {
		if(bpRepo.existsByFlight(flight)) {
			return false;
		}
		BoardingPass boardingPass = new BoardingPass(flight, user, seat, allLuggage);
		bpRepo.save(boardingPass);
		return true;
	}

	@Override
	public boolean insertNewBoardingPassByObject(BoardingPass boardingPass) {
		if(bpRepo.existsByFlight(boardingPass.getFlight())) {
			return false;
		}
		BoardingPass boardPass = new BoardingPass(boardingPass.getFlight(), boardingPass.getUser(), boardingPass.getSeat(), boardingPass.getAllLuggage());
		bpRepo.save(boardPass);
		return true;
	}

	@Override
	public void saveTestingData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<BoardingPass> selectAllBoardingPassesByFlightID(int id)throws Exception {
		if(id > 0)
		{
			if(bpRepo.existsByFlightIDFlight(id)) {
			ArrayList<BoardingPass>flightBoardingPass = bpRepo.findByFlightIDFlight(id);
			return flightBoardingPass;
			}
		}
		throw new Exception("Id is not correct and there is not customer with that id in System");
		
	}

	@Override
	public boolean deleteBoardingPassById(int id) {
		if(id>0) {
			if(bpRepo.existsById(id)) {
			if(lugRepo.existsByBoardingPassIDBPass(id)) {
				ArrayList<Luggage> luggageToRemove=lugRepo.findByBoardingPassIDBPass(id);
				lugRepo.deleteAll(luggageToRemove);
			}
		
			
				bpRepo.deleteById(id);
				return true;
			}
			
		}
		return false;
	}

	@Override
	public ArrayList<BoardingPass> selectAllBoardingPassesByUserID(User user) throws Exception {
		return bpRepo.findAllByUser(user);
	}

	
}
