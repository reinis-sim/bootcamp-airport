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
import com.example.demo.repos.IUserRepo;
import com.example.demo.services.IBoardingPassService;

@Service
public class BoardingPassServiceImpl implements IBoardingPassService{
	
	@Autowired
	IBoardingPassRepo bpRepo;
	@Autowired
	IUserRepo userRepo;

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
	public ArrayList<BoardingPass> selectAllBoardingPassesByFlight(Flight flight) {
		ArrayList<BoardingPass> bpByFlight = bpRepo.findByFlight(flight);
		if(bpByFlight!=null)
			return bpByFlight;
		
		return new ArrayList<>();
	}

}