package com.expenseTracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
     private Long paymentMethodId;
     private String paymentMethod;
     private int amount;
     private String description;
     private LocalDate date;
}
