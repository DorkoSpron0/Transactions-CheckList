package com.nicky;

import java.util.List;

public interface GetTransactions {
    List<TransactionDomain> getTransactionsByUserId(Long userId, PageRequestCustom pageRequestCustom);
}
