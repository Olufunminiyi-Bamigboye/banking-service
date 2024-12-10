package com.reagryan.online_banking.service.impl;


import com.reagryan.online_banking.dto.request.TransactionRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.entity.Transaction;
import com.reagryan.online_banking.entity.User;
import com.reagryan.online_banking.repository.TransactionRepository;
import com.reagryan.online_banking.repository.UserRepository;
import com.reagryan.online_banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public ApiResponse depositCash(Long userId, TransactionRequest request) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                User fetchedUser = user.get();
                if(request.getAmount() <= 0) {
                    throw new RuntimeException("Ooops! amount must be more than zero");
                }
                    Transaction userTransaction = new Transaction();
                    userTransaction.deposit(request.getAmount());
                    userTransaction.setAmount(request.getAmount());
                    userTransaction.setBalance(request.getBalance());
                    userTransaction.setTransactionType(request.getTransactionType());
                    userTransaction.setTransactionRef(request.getTransactionRef());
                    userTransaction.setTransactionDate(LocalDateTime.now());
                    userTransaction.setUser(request.getUser());
                    Transaction depositTransaction = transactionRepository.save(userTransaction);
                    return new ApiResponse(false, "Your money has been deposited successfully", userTransaction);
         }
            throw new RuntimeException("Oops, user with ID " + userId + " not found");
    }
}
