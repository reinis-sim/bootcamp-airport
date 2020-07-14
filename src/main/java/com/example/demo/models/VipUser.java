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
	public VipUser(String name, String surname, String email, String password, String roles, boolean isEnabled, float discount) {
		super(name, surname, email, password, roles, isEnabled);
		this.discount = discount;
	}
	public VipUser(User user, float discount) {
		super(user.getName(),user.getSurname(),user.getEmail(),user.getPassword(),user.getRoles(),user.isEnabled());
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
