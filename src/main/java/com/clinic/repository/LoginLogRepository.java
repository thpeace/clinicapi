package com.clinic.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.clinic.model.LoginLog;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {

    List<LoginLog> findByUsernameOrderByLoginTimeDesc(String username);

    List<LoginLog> findByUserIdOrderByLoginTimeDesc(Long userId);

    List<LoginLog> findTop10ByUsernameOrderByLoginTimeDesc(String username);

    long countByUsernameAndSuccessAndLoginTimeAfter(String username, boolean success, LocalDateTime after);

    // Override to add @NonNull annotation for save method
    @Override
    @NonNull
    <S extends LoginLog> S save(@NonNull S entity);
}
