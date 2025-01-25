package com.reagryan.online_banking.service.impl;


import com.reagryan.online_banking.dto.request.TransactionRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.dto.response.TransactionResponse;
import com.reagryan.online_banking.entity.Transaction;
import com.reagryan.online_banking.entity.User;
import com.reagryan.online_banking.exception.InsufficientAmountException;
import com.reagryan.online_banking.exception.InvalidAmountException;
import com.reagryan.online_banking.exception.CustomerNotFoundException;
import com.reagryan.online_banking.repository.TransactionRepository;
import com.reagryan.online_banking.repository.UserRepository;
import com.reagryan.online_banking.service.TransactionService;
import com.reagryan.online_banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Override
    public ApiResponse cashDeposit(Long userId, TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException {
        TransactionResponse response = convertTransactionToResponse(deposit(userId, request));
            return new ApiResponse(false,
                    " Your account has been successfully credited",
                    response);
    }

    @Override
    public ApiResponse cashWithdrawal(Long userId, TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException {
        return new ApiResponse(false, "Your account has been debited successfully", convertTransactionToResponse(withdrawal(userId, request)));
    }

    @Override
    public ApiResponse cashTransfer(Long senderAcct, Long recipient, TransactionRequest request) throws InvalidAmountException, CustomerNotFoundException {
        Transaction senderResponse = withdrawal(senderAcct, request);
        senderResponse.setTransactionType("Money-out");
        deposit(recipient, request);
        return new ApiResponse(false,
                " Your have successfully transferred " + senderResponse.getAmount() + " to " + recipient,
                convertTransactionToResponse(senderResponse));
    }

    public Transaction deposit(Long userId, TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException {
        if (request.getAmount() <= 0) {
            throw new InvalidAmountException("Ooops! amount must be more than zero");
        }
        return userRepository.findById(userId).map(user -> {
            Transaction depositTransaction = new Transaction();
            depositTransaction.setAmount(request.getAmount());
            depositTransaction.setTransactionDate(LocalDateTime.now());
            depositTransaction.setUser(user);
            depositTransaction.setTransactionType("Money-in");
            depositTransaction.setTransactionRef(1000 + (int) (Math.random() * 9999) + "D");

            user.setBalance(user.getBalance() + request.getAmount());
            Transaction userTransaction = transactionRepository.save(depositTransaction);
            return userTransaction;
        }).orElseThrow(() -> new CustomerNotFoundException("Oops, user with ID " + userId + " not found"));
    }

    public Transaction withdrawal(Long userId, TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException {
        if (request.getAmount() <= 0) {
            throw new InvalidAmountException("Invalid amount");
        }
        return userRepository.findById(userId).map(accountHolder -> {
            if (accountHolder.getBalance() < request.getAmount()) {
                try {
                    throw new InsufficientAmountException("Insufficient balance");
                } catch (InsufficientAmountException e) {
                    throw new RuntimeException(e);
                }
            }
            Transaction withdrawalTransaction = new Transaction();
            withdrawalTransaction.setAmount(request.getAmount());
            withdrawalTransaction.setTransactionDate(LocalDateTime.now());
            withdrawalTransaction.setUser(accountHolder);
            withdrawalTransaction.setTransactionType("Money-out");
            withdrawalTransaction.setTransactionRef(1000 + (int) (Math.random() * 8999) + "W");

            accountHolder.setBalance(accountHolder.getBalance() - request.getAmount());
            Transaction userTransaction = transactionRepository.save(withdrawalTransaction);
            return userTransaction;
        }).orElseThrow(() -> new CustomerNotFoundException("Oops, user with ID " + userId + " not found"));
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
