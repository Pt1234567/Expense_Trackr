package com.expenseTracker.service;

import com.expenseTracker.entity.PaymentMethods;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethods findPaymentMethodByType(String type) throws Exception;
    PaymentMethods findPaymentMethodById(Long id) throws Exception;

    List<PaymentMethods> getAllPaymentMethods();
}
