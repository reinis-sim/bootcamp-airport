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

@Getter @Setter @NoArgsConstructor @ToString
@Table(name = "FlightTable")
@Entity
public class Flight {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_Flight")
	@Setter(AccessLevel.NONE)
	private int ID_Flight;
	
	
	@Column(name = "Time")
	private float time; //TODO change to Time data type
	
	
	@Min(0)
	@Max(10)
	@Column(name = "Duration")
	private float duration;
	
	
	@ManyToMany(mappedBy = "flights")
	private Collection<Airport> airports;
	
	
	@Min(0)
	@Max(10)
	@Column(name = "MaxNumberOfPassangers")
	private int maxNumberOfPassangers;
	
	
	@OneToMany(mappedBy = "flight")
	private Collection<BoardingPass> boardingPasses;


	public Flight(float time, @Min(0) @Max(10) float duration, Collection<Airport> airports,
			@Min(0) @Max(10) int maxNumberOfPassangers, Collection<BoardingPass> boardingPasses) {
		super();
		this.time = time;
		this.duration = duration;
		this.airports = airports;
		this.maxNumberOfPassangers = maxNumberOfPassangers;
		this.boardingPasses = boardingPasses;
	}
	
	
	
	
	
	
	

}
