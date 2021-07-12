package com.silverlining.repository;

import com.silverlining.model.Symptoms;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomsRepository extends CrudRepository<Symptoms, Long> {
    List<Symptoms> findAllByType(String type);
}