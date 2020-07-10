package com.example.demo.models;

import javax.persistence.Column;

public class VipUser extends User{
	@Column(name="Discount")
	private float discount;

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public VipUser(String email, String password, float discount) {
		super(email, password);
		this.discount = discount;
	}
	public VipUser() {
		super();
		this.discount = 0f;
	}

	@Override
	public String toString() {
		return "VipUser [discount=" + discount + "]";
	}
	
}
