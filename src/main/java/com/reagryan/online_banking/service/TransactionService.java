package com.reagryan.online_banking.service;

import com.reagryan.online_banking.dto.request.TransactionRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;

public interface TransactionService {
    ApiResponse depositCash(Long userId, TransactionRequest request);
}