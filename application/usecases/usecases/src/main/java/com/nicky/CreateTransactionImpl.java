package com.nicky;

public class CreateTransactionImpl implements CreateTransaction{

    private final TransactionRepositoryPort transactionRepository;

    public CreateTransactionImpl(TransactionRepositoryPort transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDomain createTransaction(Long userId, TransactionDomain transaction) {
        return transactionRepository.save(userId, transaction);
    }
}
