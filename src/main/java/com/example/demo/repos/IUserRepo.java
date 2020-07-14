package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.User;

public interface IUserRepo extends CrudRepository<User, Integer> {
	User findByEmail(String email);
	boolean existsByEmail(String email);
	
}
