package com.reagryan.online_banking.service;

import com.reagryan.online_banking.dto.request.TransactionRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;

import java.util.Optional;

public interface TransactionService {
    ApiResponse cashDeposit(Long userId, TransactionRequest request);
    ApiResponse cashWithdrawal(Long userId, TransactionRequest request);
}
