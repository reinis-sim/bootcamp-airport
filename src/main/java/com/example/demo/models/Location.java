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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Table(name = "LocationTable")
@Entity
public class Location {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_Location")
	private int ID_Location;
	
	
	@Size(min = 3, max = 15)
	@Pattern(regexp = "[a-zA-Z\\s]+$", message = "Only letters and spaces")
	@Column(name = "City")
	private String city;
	
	
	@Size(min = 3, max = 15)
	@Pattern(regexp = "[a-zA-Z\\s]+$", message = "Only letters and spaces")
	@Column(name = "Country")
	private String country;
	
	
	@OneToMany(mappedBy = "location")
	private Collection<Airport> airport;
	
	public Location()
	{
		
	}
	
	


	public Location(
			@Size(min = 3, max = 15) @Pattern(regexp = "[a-zA-Z\\s]+$", message = "Only letters and spaces") String city,
			@Size(min = 3, max = 15) @Pattern(regexp = "[a-zA-Z\\s]+$", message = "Only letters and spaces") String country/*, Airport airport*/) {
		super();
		this.city = city;
		this.country = country;
	//	this.airport = (Collection<Airport>) airport;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getCountry() {
		return country;
	}




	public void setCountry(String country) {
		this.country = country;
	}




	public Collection<Airport> getAirport() {
		return airport;
	}




	public void setAirport(Collection<Airport> airport) {
		this.airport = airport;
	}




	public int getID_Location() {
		return ID_Location;
	}




	@Override
	public String toString() {
		return "Location [ID_Location=" + ID_Location + ", city=" + city + ", country=" + country + ", airport="
				+ airport + "]";
	}
	
	

	
	
	

}
