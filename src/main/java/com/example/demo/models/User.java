package com.example.demo.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID_User")
	private int id_user;
	
	@Column(name="Email", unique = true)
	private String email;
	
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
	
	public User(String email, String password, String roles, boolean isEnabled) {
		super();
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.isEnabled = isEnabled;
	}
	public User() {
		super();
		this.email = null;
		this.password = null;
		this.roles = null;
		this.isEnabled = true;
	}
	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", email=" + email + ", password=" + password + "]";
	}
	
}
