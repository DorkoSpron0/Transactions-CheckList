package com.nicky;

public class DeleteTransactionImpl implements DeleteTransaction{

    private final TransactionRepositoryPort transactionRepository;

    public DeleteTransactionImpl(TransactionRepositoryPort transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public String deleteTransaction(Long transactionId) {
        return transactionRepository.deleteTransaction(transactionId);
    }
}
