package com.example.demo.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_User")
	private int id_user;
	@Column(name="Name")
	@Size(min = 5, max=30, message = "Name must be atleast 5-30 characters long")
	private String name;
	@Column(name="Surname")
	@Size(min = 5, max=30, message = "Surname must be atleast 5-30 characters long")
	private String surname;
	@Email(message = "Fill in correct email")
	@Column(name="Email", unique = true)
	private String email;
	@Size(min = 5, message = "Password must be atleast 5 characters long")
	@Column(name="Password")
	private String password;
	
	@Column(name="Roles")
	private String roles;
	
	@Column(name="IsEnabled")
	private boolean isEnabled;
	
	@OneToMany(mappedBy = "user")
	private Collection<BoardingPass> boardingPasses;
	
	
	public int getId_user() {
		return id_user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public boolean isEnabled() {
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public User(@Size(min = 5, max = 30, message = "Name must be atleast 5-30 characters long") String name,
			@Size(min = 5, max = 30, message = "Surname must be atleast 5-30 characters long") String surname,
			@Email(message = "Fill in correct email") String email,
			@Size(min = 5, message = "Password must be atleast 5 characters long") String password, String roles,
			boolean isEnabled) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.isEnabled = isEnabled;
	}
	public User() {
		super();
		this.name = null;
		this.surname = null;
		this.email = null;
		this.password = null;
		this.roles = null;
		this.isEnabled = false;
	}
	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", email=" + email + ", password=" + password + "]";
	}
	
}
