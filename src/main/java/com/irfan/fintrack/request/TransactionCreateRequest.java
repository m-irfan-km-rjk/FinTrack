package com.irfan.fintrack.request;

import com.irfan.fintrack.model.TransactionType;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCreateRequest {

    @NotBlank
    private String title;

    private String description;

    @Positive
    private double amount;

    @NotNull
    private TransactionType type;

    private LocalDate date;

    private Long userId; // Remove this later when you add JWT authentication.
}