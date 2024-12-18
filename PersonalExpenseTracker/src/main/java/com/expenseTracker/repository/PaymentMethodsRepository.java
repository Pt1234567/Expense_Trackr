package com.expenseTracker.repository;

import com.expenseTracker.entity.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodsRepository extends JpaRepository<PaymentMethods,Long> {
    PaymentMethods findByType(String type);
}
