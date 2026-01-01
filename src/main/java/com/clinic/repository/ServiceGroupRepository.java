package com.clinic.repository;

import com.clinic.model.ServiceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceGroupRepository extends JpaRepository<ServiceGroup, Long> {
    List<ServiceGroup> findByActiveTrue();
}
