package com.example.demo.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Table(name = "AirportTable")
@Entity
public class Airport {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IDAirport")
	private int IDAirport;
	
	
	@Size(min = 1, max = 20)
	@Pattern(regexp = "[a-zA-Z\\s]+$", message = "Only letters and spaces")
	@Column(name = "Title")
	private String title;
	
	@ManyToMany(mappedBy = "airports")
	private Collection<Flight> flights;
	
	
	
	@ManyToOne
	@JoinColumn(name = "ID_Location")
	private Location location;

	
	public Airport()
	{
		
	}


	public Airport(
			@Size(min = 3, max = 20) @Pattern(regexp = "[a-zA-Z\\s]+$", message = "Only letters and spaces") String title,
			Collection<Flight> flights, Location location) {
		super();
		this.title = title;
		this.flights = flights;
		this.location = location;
	}




	public Airport(
			@Size(min = 1, max = 20) @Pattern(regexp = "[a-zA-Z\\s]+$", message = "Only letters and spaces") String title,
			Location location) {
		super();
		this.title = title;
		this.location = location;
		this.flights = Collections.emptyList();
	}
	
	public boolean addNewFlight(Flight flight)
	{
		if (flights.contains(flight))
			return false;
		else
			{flights.add(flight);
			return true;}
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Collection<Flight> getFlights() {
		return flights;
	}


	public void setFlights(Collection<Flight> flights) {
		this.flights = flights;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public int getIDAirport() {
		return IDAirport;
	}


	@Override
	public String toString() {
		return "Airport [IDAirport=" + IDAirport + ", title=" + title + "]";
	}

}
