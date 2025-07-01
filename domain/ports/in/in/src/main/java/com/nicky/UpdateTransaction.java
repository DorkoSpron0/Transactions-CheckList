package com.nicky;

public interface UpdateTransaction {
    TransactionDomain updateTransaction(Long transactionId, TransactionDomain newTransaction);
}
