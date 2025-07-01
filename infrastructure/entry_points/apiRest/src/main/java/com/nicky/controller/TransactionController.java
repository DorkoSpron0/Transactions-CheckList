package com.nicky.controller;

import com.nicky.DTO.TransactionDTO;
import com.nicky.DTO.UserDTO;
import com.nicky.PageRequestCustom;
import com.nicky.TransactionDomain;
import com.nicky.TransactionService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionDomain>> getTransactions(@PathVariable Long userId,@PageableDefault(page = 0, size = 20) Pageable pageable){
        PageRequestCustom pageRequestCustom = new PageRequestCustom(pageable.getPageNumber(), pageable.getPageSize());
        return new ResponseEntity<>(transactionService.getTransactionsByUserId(userId, pageRequestCustom), HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<TransactionDTO> createTransaction(@PathVariable Long userId, @RequestBody TransactionDomain transaction){
        TransactionDomain transactionDomain = transactionService.createTransaction(userId, transaction);

        // TODO -> Refactor to fromDomain/toDomain
        TransactionDTO dto = TransactionDTO.builder()
                .id(transactionDomain.getId())
                .title(transactionDomain.getTitle())
                .description(transactionDomain.getDescription())
                .dateTime(LocalDateTime.now())
                .amount(transactionDomain.getAmount())
                .isCompleted(transactionDomain.isCompleted())
                .user(new UserDTO(
                        transactionDomain.getUser().getId(),
                        transactionDomain.getUser().getUsername(),
                        transactionDomain.getUser().getEmail(),
                        transactionDomain.getUser().getPassword()
                ))
                .build();

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
