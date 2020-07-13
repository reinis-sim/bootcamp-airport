package com.example.demo.models;

import java.util.Collection;

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

@Getter @Setter @NoArgsConstructor @ToString
@Table(name = "AirportTable")
@Entity
public class Airport {
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_Airport")
	@Setter(AccessLevel.NONE)
	private int ID_Airport;
	
	
	@Size(min = 3, max = 20)
	@Pattern(regexp = "[a-zA-Z\\s]+$", message = "Only letters and spaces")
	@Column(name = "Title")
	private String title;
	
	
	
	@ManyToMany
	@JoinTable(name = "Airport_Flight", joinColumns =@JoinColumn(name = "ID_Flight" ), inverseJoinColumns = @JoinColumn(name = "ID_Airport" ) )
	private Collection<Flight> flights;
	
	
	
	@ManyToOne
	@JoinColumn(name = "ID_Location")
	private Location location;




	public Airport(
			@Size(min = 3, max = 20) @Pattern(regexp = "[a-zA-Z\\s]+$", message = "Only letters and spaces") String title,
			Collection<Flight> flights, Location loacation) {
		super();
		this.title = title;
		this.flights = flights;
		this.location = loacation;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
