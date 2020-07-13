package com.example.demo.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.BoardingPass;

public interface IBoardingPassRepo extends CrudRepository<BoardingPass, Integer> {

}
