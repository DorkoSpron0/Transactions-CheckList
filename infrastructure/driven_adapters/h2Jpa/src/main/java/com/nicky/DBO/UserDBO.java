package com.nicky.DBO;

import com.nicky.TransactionDomain;
import com.nicky.UserDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class UserDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<TransactionDBO> transactions;

    public static UserDBO fromDomain(UserDomain user){
        return new UserDBO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getTransactions() != null ? user.getTransactions()
                        .stream()
                        .map(transactionDomain -> new TransactionDBO(
                                transactionDomain.getId(),
                                transactionDomain.getTitle(),
                                transactionDomain.getDescription(),
                                transactionDomain.getDateTime(),
                                transactionDomain.getAmount(),
                                transactionDomain.isCompleted(),
                                null
                        )).toList() : null
        );
    }

    public UserDomain toDomain(){
        return new UserDomain(
                id,
                username,
                email,
                password,
                transactions != null ? transactions
                        .stream()
                        .map(transactionDBO -> new TransactionDomain(
                                transactionDBO.getId(),
                                transactionDBO.getTitle(),
                                transactionDBO.getDescription(),
                                transactionDBO.getDateTime(),
                                transactionDBO.getAmount(),
                                transactionDBO.isCompleted(),
                                null
                        )).toList() : null
        );
    }
}
