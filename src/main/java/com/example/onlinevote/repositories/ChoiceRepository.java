package com.example.onlinevote.repositories;

import com.example.onlinevote.models.Choice;

import org.springframework.data.repository.CrudRepository;

public interface ChoiceRepository extends CrudRepository<Choice, Integer> {
}
