package com.reagryan.online_banking.service.impl;

import com.reagryan.online_banking.dto.request.TransactionRequest;
import com.reagryan.online_banking.entity.User;
import com.reagryan.online_banking.repository.TransactionRepository;
import com.reagryan.online_banking.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceImplTest {
    @MockBean
    TransactionRepository transactionRepository;

    @MockBean
    UserRepository userRepository;

    @Autowired
    TransactionServiceImpl transactionService;

//    @Test
//    void testCashDeposit() {
//        User existingUser = new User(1L, "My firstName", "My lastName", "My phoneNo", "male", "My email", LocalDateTime.of(2021, 10, 15, 11, 55));
//
//        TransactionRequest transactionRequest = new TransactionRequest();
//        transactionRequest.setAmount(100);
//        transactionRequest.setUser(existingUser);
//        transactionRequest.setTransactionType("deposit");
//        transactionRequest.setTransactionRef(1000 + (int)(Math.random() * 9999) + "D");
//        LocalDateTime transactionDate = LocalDateTime.of(2021, 10, 15, 11, 55);
//
//        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
//        Mockito.when(transactionRepository.save(existingUser)).thenAnswer(invocationOnMock -> );
//
//
//    }

}