package com.reagryan.online_banking.service;

import com.reagryan.online_banking.dto.request.TransactionRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.exception.InvalidAmountException;
import com.reagryan.online_banking.exception.CustomerNotFoundException;

public interface TransactionService {
    ApiResponse cashDeposit(Long userId, TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException;
    ApiResponse cashWithdrawal(Long userId, TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException;
//    ApiResponse cashTransfer(Long userIdFrom, Long userIdTo, TransactionRequest request);
}
