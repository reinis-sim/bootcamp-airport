package com.example.demo.models;

import java.sql.Time;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Table(name = "FlightTable")
@Entity
public class Flight {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_Flight")
	private int ID_Flight;
	
	
	@Column(name = "Time")
	private float time; //TODO change to Time data type
	
	
	@Min(0)
	@Max(1000)
	@Column(name = "Duration")
	private float duration;
	
	
	@ManyToMany(mappedBy = "flights")
	private Collection<Airport> airports;
	
	
	@Min(0)
	@Max(1000)
	@Column(name = "MaxNumberOfPassangers")
	private int maxNumberOfPassangers;
	
	
	@OneToMany(mappedBy = "flight")
	private Collection<BoardingPass> boardingPasses;
	
	public Flight()
	{
		
	}


	public Flight(float time, @Min(0) @Max(1000) float duration, Collection<Airport> airports,
			@Min(0) @Max(1000) int maxNumberOfPassangers /*, Collection<BoardingPass> boardingPasses*/) {
		super();
		this.time = time;
		this.duration = duration;
		this.airports = airports;
		this.maxNumberOfPassangers = maxNumberOfPassangers;
	//	this.boardingPasses = boardingPasses;
	}


	public float getTime() {
		return time;
	}


	public void setTime(float time) {
		this.time = time;
	}


	public float getDuration() {
		return duration;
	}


	public void setDuration(float duration) {
		this.duration = duration;
	}


	public Collection<Airport> getAirports() {
		return airports;
	}


	public void setAirports(Collection<Airport> airports) {
		this.airports = airports;
	}


	public int getMaxNumberOfPassangers() {
		return maxNumberOfPassangers;
	}


	public void setMaxNumberOfPassangers(int maxNumberOfPassangers) {
		this.maxNumberOfPassangers = maxNumberOfPassangers;
	}


	public Collection<BoardingPass> getBoardingPasses() {
		return boardingPasses;
	}


	public void setBoardingPasses(Collection<BoardingPass> boardingPasses) {
		this.boardingPasses = boardingPasses;
	}


	public int getID_Flight() {
		return ID_Flight;
	}


	@Override
	public String toString() {
		return "Flight [ID_Flight=" + ID_Flight + ", time=" + time + ", duration=" + duration + ", airports=" + airports
				+ ", maxNumberOfPassangers=" + maxNumberOfPassangers + ", boardingPasses=" + boardingPasses + "]";
	}
	
	
	
	
	
	
	
	
	

}
