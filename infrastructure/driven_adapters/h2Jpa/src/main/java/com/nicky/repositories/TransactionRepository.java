package com.nicky.repositories;

import com.nicky.DBO.TransactionDBO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionDBO, Long> {
}
