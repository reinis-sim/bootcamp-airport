package com.example.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.demo.models.Airport;
import com.example.demo.models.BoardingPass;
import com.example.demo.models.Flight;
import com.example.demo.models.Luggage;
import com.example.demo.models.User;
import com.example.demo.repos.IBoardingPassRepo;
import com.example.demo.repos.IFlightRepo;
import com.example.demo.repos.IUserRepo;
import com.example.demo.services.IUserService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfDocument;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserRepo userRepo;
	@Autowired
	IFlightRepo flightRepo;
	@Autowired
	IBoardingPassRepo boardingRepo;
	
	@Override
	public boolean register(String name, String surname, String email, String password) {
		if(!userRepo.existsByEmail(email)) {
			userRepo.save(new User(name,surname,email,BCrypt.hashpw(password, BCrypt.gensalt()),"ROLE_USER",true));
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean authenticate(String email, String password) {
		if(userRepo.existsByEmail(email)) {
			if(BCrypt.checkpw(password, userRepo.findByEmail(email).getPassword())) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean bookFlight(User user, Flight flight, ArrayList<Luggage>allLuggage) {
		if(!boardingRepo.existsByUserAndFlight(user, flight)) {
			//TODO create seat logic
			boardingRepo.save(new BoardingPass(flight, user, 0, allLuggage));
			return true;
		}else {
			return false;
		}
	}

	@Override
	public ArrayList<Flight> selectAllFlights() {
		return (ArrayList<Flight>) flightRepo.findAll();
	}
	
	@Override
	public ArrayList<Flight> selectAllFlightsInAirport(Airport airport) {
		//TODO doesnt work ?
		return flightRepo.findAllByAirports(airport);
	}
	
	@Override
	public ArrayList<Flight> selectBookedFlightsByUser(User user) {
		return flightRepo.findAllByBoardingPassesUser(user);
	}

	@Override
	public PdfDocument exportBookedFlightAsPDF(BoardingPass boardingPass) {
		PdfDocument pdfDoc = new PdfDocument();
		pdfDoc.newPage();
		try {
			pdfDoc.add((Element) boardingPass);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		pdfDoc.close();
		return pdfDoc;
	}
	
	@Override
	public Flight selectOneBookedFlightByUser(BoardingPass boardingPass) {
		return flightRepo.findByBoardingPasses(boardingPass);
	}

	

}
