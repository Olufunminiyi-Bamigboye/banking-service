package com.reagryan.online_banking.service;

import com.reagryan.online_banking.dto.request.TransactionRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.dto.response.TransactionResponse;
import com.reagryan.online_banking.exception.InvalidAmountException;
import com.reagryan.online_banking.exception.CustomerNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    ApiResponse cashDeposit(Long userId, TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException;
    ApiResponse cashWithdrawal(Long userId, TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException;
    ApiResponse cashTransfer(Long senderAcct, Long recipient, TransactionRequest request) throws InvalidAmountException, CustomerNotFoundException;
    ApiResponse depositTransactionsByUser(Long userId, String transactionType) throws CustomerNotFoundException;
    ApiResponse transferTransactionsByUser(Long userId, String transactionType) throws CustomerNotFoundException;
    ApiResponse <Page<List<TransactionResponse>>> fetchAllTransactions(int page, int size, String sortBy, String direction);
}
