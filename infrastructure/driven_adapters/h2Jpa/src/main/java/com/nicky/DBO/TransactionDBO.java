package com.nicky.DBO;

import com.nicky.TransactionDomain;
import com.nicky.UserDomain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class TransactionDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private Long amount;
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDBO user;

    public static TransactionDBO fromDomain(TransactionDomain transaction){
        return new TransactionDBO(
                transaction.getId(),
                transaction.getTitle(),
                transaction.getDescription(),
                transaction.getDateTime(),
                transaction.getAmount(),
                transaction.isCompleted(),
                transaction.getUser() != null ? new UserDBO(
                        transaction.getUser().getId(),
                        transaction.getUser().getUsername(),
                        transaction.getUser().getEmail(),
                        transaction.getUser().getPassword(),
                        null
                ) : null
        );
    }

    public TransactionDomain toDomain(){
        return new TransactionDomain(
                id,
                title,
                description,
                dateTime,
                amount,
                isCompleted,
                user != null ? new UserDomain(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        null
                ) : null
        );
    }
}
