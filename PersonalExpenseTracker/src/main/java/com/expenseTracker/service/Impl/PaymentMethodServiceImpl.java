package com.expenseTracker.service.Impl;

import com.expenseTracker.entity.PaymentMethods;
import com.expenseTracker.repository.PaymentMethodsRepository;
import com.expenseTracker.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodsRepository paymentMethodsRepository;

    @Override
    public PaymentMethods findPaymentMethodByType(String type) throws Exception {
        PaymentMethods paymentMethods=paymentMethodsRepository.findByType(type);
        if(paymentMethods==null) throw  new Exception("Payment method not found");
        return paymentMethods;
    }

    @Override
    public PaymentMethods findPaymentMethodById(Long id) throws Exception {
        PaymentMethods paymentMethods=paymentMethodsRepository.findById(id).orElse(null);
        if (paymentMethods==null) throw new Exception("Payment method not found");
        return paymentMethods;
    }

    @Override
    public List<PaymentMethods> getAllPaymentMethods() {
        return paymentMethodsRepository.findAll();
    }
}
