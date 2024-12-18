package com.expenseTracker.controller;

import com.expenseTracker.DTO.ExpenseDTO;
import com.expenseTracker.entity.Expense;
import com.expenseTracker.entity.PaymentMethods;
import com.expenseTracker.entity.User;
import com.expenseTracker.service.ExpenseService;
import com.expenseTracker.service.PaymentMethodService;
import com.expenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @PostMapping("/expense")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO, @RequestHeader("Authorization") String jwt) throws Exception {
        PaymentMethods paymentMethods=paymentMethodService.findPaymentMethodByType(expenseDTO.getPaymentMethod());
        User user=userService.findUserByJwt(jwt);

        Expense expense=new Expense();
        expense.setAmount(expense.getAmount());
        expense.setPaymentMethods(paymentMethods);
        expense.setUser(user);
        expense.setDescription(expenseDTO.getDescription());
        expense.setCreatedAt(expenseDTO.getDate());
        expense.setUpdatedAt(expenseDTO.getDate());
        expenseService.createExpense(expense);

        return new ResponseEntity<>(expenseDTO, HttpStatus.CREATED);
    }


    @GetMapping("/expense")
    public ResponseEntity<List<ExpenseDTO>> getAllExpense(){
        List<Expense> expenseList=expenseService.getAllExpenses();
        List<ExpenseDTO> expenseDTOList=new ArrayList<>();
        for(Expense exp:expenseList){
            ExpenseDTO expenseDTO=new ExpenseDTO();
            expenseDTO.setPaymentMethodId(exp.getId());
            expenseDTO.setPaymentMethod(exp.getPaymentMethods().getType());
            expenseDTO.setAmount(exp.getAmount());
            expenseDTO.setDescription(exp.getDescription());
            expenseDTO.setDate(exp.getCreatedAt());
            expenseDTOList.add(expenseDTO);
        }
        return new ResponseEntity<>(expenseDTOList,HttpStatus.OK);
    }

    @GetMapping("/expense/paymentMethod")
    public ResponseEntity<List<PaymentMethods>> getAllPaymentMethod(){
        List<PaymentMethods> paymentMethods=paymentMethodService.getAllPaymentMethods();
        return new ResponseEntity<>(paymentMethods,HttpStatus.OK);
    }

    @GetMapping("/expense/paymentMethod/{paymentMethod}")
    public ResponseEntity<List<ExpenseDTO>> getAllExpensesOfPaymentMethod(@PathVariable String paymentMethod){
        List<Expense> expenseList=expenseService.getAllExpenses();
        List<ExpenseDTO> expenseDTOList=new ArrayList<>();
        for(Expense exp:expenseList){
            if(!exp.getPaymentMethods().getType().equals(paymentMethod))continue;
            ExpenseDTO expenseDTO=new ExpenseDTO();
            expenseDTO.setPaymentMethodId(exp.getId());
            expenseDTO.setPaymentMethod(exp.getPaymentMethods().getType());
            expenseDTO.setAmount(exp.getAmount());
            expenseDTO.setDescription(exp.getDescription());
            expenseDTO.setDate(exp.getCreatedAt());
            expenseDTOList.add(expenseDTO);
        }
        return new ResponseEntity<>(expenseDTOList,HttpStatus.OK);
    }

}
