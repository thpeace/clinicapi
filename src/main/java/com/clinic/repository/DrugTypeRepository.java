package com.clinic.repository;

import com.clinic.model.DrugType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugTypeRepository extends JpaRepository<DrugType, Long> {
    List<DrugType> findByActiveTrue();
}
