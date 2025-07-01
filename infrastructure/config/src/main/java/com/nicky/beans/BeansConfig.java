package com.nicky.beans;

import com.nicky.*;
import com.nicky.adapters.TransactionRepositoryAdapter;
import com.nicky.adapters.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort){
        return new UserService(
                new RegisterUserImpl(userRepositoryPort),
                new LoginUserImpl(userRepositoryPort)
        );
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserRepositoryAdapter userRepositoryAdapter){
        return userRepositoryAdapter;
    }

    @Bean
    public TransactionService transactionService(TransactionRepositoryPort transactionRepositoryPort){
        return new TransactionService(
                new CreateTransactionImpl(transactionRepositoryPort),
                new GetTransactionsImpl(transactionRepositoryPort),
                new UpdateTransactionImpl(transactionRepositoryPort),
                new DeleteTransactionImpl(transactionRepositoryPort)
        );
    }

    @Bean
    public TransactionRepositoryPort transactionRepositoryPort(TransactionRepositoryAdapter transactionRepositoryAdapter){
        return transactionRepositoryAdapter;
    }
}
