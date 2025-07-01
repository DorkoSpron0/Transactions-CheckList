package com.nicky;

public interface CreateTransaction {

    TransactionDomain createTransaction(Long userId, TransactionDomain transaction);
}
