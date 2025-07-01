package com.nicky;

public class UpdateTransactionImpl implements UpdateTransaction{

    private final TransactionRepositoryPort transactionRepository;

    public UpdateTransactionImpl(TransactionRepositoryPort transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDomain updateTransaction(Long transactionId, TransactionDomain newTransaction) {
        return transactionRepository.updateTransactionById(transactionId, newTransaction);
    }
}
