package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
@Table(name = "Luggage")
@Entity
public class Luggage {
	
	
	//PRICE FOR LUGGAGE
	@Min(0)
	private float price;
	
	
	//LUGGAGE WEIGHT
	@Min(0)
	private float weight;
	
	//BOARDING PASS
	@ManyToOne
	@JoinColumn(name="ID_BoardingPass")
	private BoardingPass boardingPass;

	
	public Luggage() {
		
	}
	public Luggage(float price, float weight, BoardingPass boardingPass) {
		super();
		this.price = price;
		this.weight = weight;
		this.boardingPass = boardingPass;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public BoardingPass getBoardingPass() {
		return boardingPass;
	}
	public void setBoardingPass(BoardingPass boardingPass) {
		this.boardingPass = boardingPass;
	}
	
	
}
