package com.reagryan.online_banking.service.impl;


import com.reagryan.online_banking.dto.request.TransactionRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.dto.response.TransactionResponse;
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
    public ApiResponse cashDeposit(Long userId, TransactionRequest request) {
//            Optional<User> user = userRepository.findById(userId);
//            if (user.isPresent()) {
//                User fetchedUser = user.get();
                if(request.getAmount() <= 0) {
                    throw new RuntimeException("Ooops! amount must be more than zero");
                }
                return userRepository.findById(userId).map(user -> {
                    Transaction depositTransaction = new Transaction();
                    depositTransaction.setAmount(request.getAmount());
                    depositTransaction.setTransactionDate(LocalDateTime.now());
                    depositTransaction.setUser(user);
                    depositTransaction.setTransactionType("deposit");
                    depositTransaction.setTransactionRef(1000 + (int)(Math.random() * 9999) + "F");

                    user.setBalance(user.getBalance() + request.getAmount());
                    Transaction userTransaction = transactionRepository.save(depositTransaction);
                    TransactionResponse response = convertTransactionToResponse(userTransaction);
                    return new ApiResponse(false,
                            " Your account has been successfully credited",
                            response);
                }).orElseThrow(() -> new RuntimeException("Oops, user with ID " + userId + " not found"));
        }

        public TransactionResponse convertTransactionToResponse(Transaction transaction) {
            TransactionResponse transactionResponse = new TransactionResponse(
                    transaction.getUser().getBalance(),
                    transaction.getAmount(),
                    transaction.getTransactionType(),
                    transaction.getTransactionRef(),
                    transaction.getTransactionDate()
            );
            return transactionResponse;
        }
    }
