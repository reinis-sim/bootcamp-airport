package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Luggage;

public interface ILuggageRepo extends CrudRepository<Luggage, Integer> {

}
