package com.nicky.repositories;

import com.nicky.DBO.UserDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDBO, Long> {
    Optional<UserDBO> findByEmail(String email);
}
