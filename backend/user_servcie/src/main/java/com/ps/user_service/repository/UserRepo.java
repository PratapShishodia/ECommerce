package com.ps.user_service.repository;

import com.ps.user_service.model.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    @Query("select u from Users u")
    Page<Users> findAll(Pageable pageable);
    Optional<Users> findByActivationToken(String activationToken);
    boolean existsByEmail(String email);
    boolean existsByMobileNumber(String mobileNumber);
    boolean existsByEmailAndUserIdNot(String email,Long userId);
    boolean existsByMobileNumberAndUserIdNot(String mobileNumber,Long userId);
}
