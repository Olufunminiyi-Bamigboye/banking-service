package com.reagryan.online_banking.controller;

import com.reagryan.online_banking.dto.request.TransactionRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class TransactionController {
    @Autowired
    private TransactionServiceImpl transactionService;

    @PutMapping("transactions/{id}/deposits")
    public ResponseEntity<ApiResponse> depositCash(@PathVariable Long id, @RequestBody TransactionRequest request){
        return ResponseEntity.ok().body(transactionService.cashDeposit(id, request));
    }
}
