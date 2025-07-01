package com.nicky.repositories;

import com.nicky.DBO.UserDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDBO, Long> {
}
