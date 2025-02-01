package com.reagryan.online_banking.controller;

import com.reagryan.online_banking.dto.request.TransactionRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.dto.response.TransactionResponse;
import com.reagryan.online_banking.exception.InvalidAmountException;
import com.reagryan.online_banking.exception.CustomerNotFoundException;
import com.reagryan.online_banking.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class TransactionController {
    @Autowired
    private TransactionServiceImpl transactionService;

    @PutMapping("transactions/{id}/deposits")
    public ResponseEntity<ApiResponse> depositCash(@PathVariable Long id, @RequestBody TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException {
        return ResponseEntity.ok().body(transactionService.cashDeposit(id, request));
    }

    @PutMapping("transactions/{id}/withdrawals")
    public ResponseEntity<ApiResponse> withdrawCash(@PathVariable Long id, @RequestBody TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException {
        return ResponseEntity.ok().body(transactionService.cashWithdrawal(id, request));
    }

    @PostMapping("transactions/{senderId}/{recipientId}/transfers")
    public ResponseEntity<ApiResponse> transferCash(@PathVariable Long senderId, @PathVariable Long recipientId, @RequestBody TransactionRequest request) throws InvalidAmountException, CustomerNotFoundException {
        return ResponseEntity.ok().body(transactionService.cashTransfer(senderId, recipientId, request));
    }

    @GetMapping("transactions/{userId}/deposits/history")
    public ResponseEntity<ApiResponse> depositHistory(@PathVariable Long userId, @RequestParam String transactionType) throws CustomerNotFoundException {
        return ResponseEntity.ok().body(transactionService.depositTransactionsByUser(userId, transactionType));
    }

    @GetMapping ("transactions")
    public ResponseEntity<ApiResponse<Page<List<TransactionResponse>>>> getAllTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "transactionDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return ResponseEntity.ok().body(transactionService.fetchAllTransactions(page, size, sortBy, direction));
    }
}
