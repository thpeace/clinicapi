package com.clinic.repository;

import com.clinic.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByStatus(String status);

    Optional<Treatment> findByTid(String tid);

    Optional<Treatment> findByBcode(String bcode);
}
