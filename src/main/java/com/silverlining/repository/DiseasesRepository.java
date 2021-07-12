package com.silverlining.repository;

import com.silverlining.model.Diseases;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseasesRepository extends CrudRepository<Diseases, Long> {
}
