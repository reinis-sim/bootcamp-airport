package com.example.demo.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	
	
	
	private Collection<Flight> flights;
	
	
	
	
	@OneToMany(mappedBy = "airport")
	private Location loacation;
	
	
	
	
	
	
	
	
	
	
	
	

}
