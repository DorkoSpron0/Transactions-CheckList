package com.nicky.adapters;

import com.nicky.DBO.TransactionDBO;
import com.nicky.DBO.UserDBO;
import com.nicky.PageRequestCustom;
import com.nicky.TransactionDomain;
import com.nicky.TransactionRepositoryPort;
import com.nicky.repositories.TransactionRepository;
import com.nicky.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionRepositoryAdapter(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TransactionDomain save(Long userId, TransactionDomain transaction) {
        UserDBO userFounded = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        TransactionDBO transactionSaved = TransactionDBO.fromDomain(transaction);
        transactionSaved.setUser(userFounded);

        return transactionRepository.save(transactionSaved).toDomain();
    }

    @Override
    @Transactional
    public List<TransactionDomain> getTransactions(Long userId, PageRequestCustom pageRequestCustom) {
        final Pageable pageable = PageRequest.of(
                pageRequestCustom.getPage(),
                pageRequestCustom.getSize()
        );
        return transactionRepository.findAll(pageable)
                .stream()
                .filter(transactionDBO -> transactionDBO.getUser().getId().equals(userId))
                .map(TransactionDBO::toDomain)
                .toList();
    }

    @Override
    public TransactionDomain updateTransactionById(Long transactionId, TransactionDomain transaction) {
        return null;
    }

    @Override
    public String deleteTransaction(Long transactionId) {
        TransactionDBO transactionFounded = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found"));

        transactionRepository.delete(transactionFounded);
        return "Transaction deleted successfully";
    }
}
