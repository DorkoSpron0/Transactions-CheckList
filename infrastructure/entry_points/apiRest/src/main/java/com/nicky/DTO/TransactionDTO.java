package com.nicky.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"transactionId", "title", "description", "dateTime", "amount", "completed", "user"})
public class TransactionDTO {
    @JsonProperty(value = "transactionId", access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String title;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:SS", timezone = "America/Bogota")
    private LocalDateTime dateTime;
    private Long amount;

    @JsonProperty(value = "completed", access = JsonProperty.Access.READ_ONLY)
    private boolean isCompleted;

    private UserDTO user;
}
