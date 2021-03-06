package com.example.demo.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "BoardingPass")
@Entity
public class BoardingPass {
	
	//BOARDING PASS ID
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_BoardingPass")
	private int IDBPass;
	
	//FLIGHT
	@ManyToOne
	@JoinColumn(name = "IDFlight")
	private Flight flight;
	
	
	//USER
	@ManyToOne
	@JoinColumn(name="ID_User")
	private User user;
	
	
	//SEAT
	@Column(name="Seat")
	private int seat;
	
	@Column(name="IsCheckedIn")
	private boolean isCheckedIn;
	
	//LUGGAGE
	@OneToMany(mappedBy="boardingPass")
	private Collection<Luggage> allLuggage;


	public BoardingPass() {
		
	}
	public BoardingPass(Flight flight, User user, int seat) {
		super();
		this.flight = flight;
		this.user = user;
		this.seat = seat;
		this.isCheckedIn = false;
	}
	
	public BoardingPass(Flight flight, User user, int seat, Collection<Luggage> allLuggage) {
		super();
		this.flight = flight;
		this.user = user;
		this.seat = seat;
		this.allLuggage = allLuggage;
		this.isCheckedIn = false;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public Collection<Luggage> getAllLuggage() {
		return allLuggage;
	}
	public void setAllLuggage(Collection<Luggage> allLuggage) {
		this.allLuggage = allLuggage;
	}
	public int getIDBPass() {
		return IDBPass;
	}
	public boolean isCheckedIn() {
		return isCheckedIn;
	}
	public void setCheckedIn(boolean isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}
	@Override
	public String toString() {
		return "BoardingPass [ID_BPass=" + IDBPass + ", flight=" + flight + ", user=" + user + ", seat=" + seat
				+ ", allLuggage=" + allLuggage + "]";
	}
	
	
	
}
