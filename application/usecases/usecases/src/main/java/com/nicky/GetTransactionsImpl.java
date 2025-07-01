package com.nicky;

import java.util.List;

public class GetTransactionsImpl implements GetTransactions{

    private final TransactionRepositoryPort transactionRepository;

    public GetTransactionsImpl(TransactionRepositoryPort transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionDomain> getTransactionsByUserId(Long userId, PageRequestCustom pageRequestCustom) {
        return transactionRepository.getTransactions(userId, pageRequestCustom);
    }
}
