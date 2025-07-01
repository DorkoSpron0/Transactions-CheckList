package com.nicky;

import java.util.List;

public class TransactionService implements CreateTransaction, GetTransactions, UpdateTransaction, DeleteTransaction{

    private final CreateTransaction createTransaction;
    private final GetTransactions getTransactions;
    private final UpdateTransaction updateTransaction;
    private final DeleteTransaction deleteTransaction;

    public TransactionService(CreateTransaction createTransaction, GetTransactions getTransactions, UpdateTransaction updateTransaction, DeleteTransaction deleteTransaction) {
        this.createTransaction = createTransaction;
        this.getTransactions = getTransactions;
        this.updateTransaction = updateTransaction;
        this.deleteTransaction = deleteTransaction;
    }

    @Override
    public TransactionDomain createTransaction(Long userId, TransactionDomain transaction) {
        return createTransaction.createTransaction(userId, transaction);
    }

    @Override
    public String deleteTransaction(Long transactionId) {
        return deleteTransaction.deleteTransaction(transactionId);
    }

    @Override
    public List<TransactionDomain> getTransactionsByUserId(Long userId, PageRequestCustom pageRequestCustom) {
        return getTransactions.getTransactionsByUserId(userId, pageRequestCustom);
    }

    @Override
    public TransactionDomain updateTransaction(Long transactionId, TransactionDomain newTransaction) {
        return updateTransaction.updateTransaction(transactionId, newTransaction);
    }
}
