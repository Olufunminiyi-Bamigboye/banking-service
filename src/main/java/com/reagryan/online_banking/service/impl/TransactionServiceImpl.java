package com.reagryan.online_banking.service.impl;


import com.reagryan.online_banking.dto.request.AuditLogRequest;
import com.reagryan.online_banking.dto.request.MetaDataRequest;
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
import com.reagryan.online_banking.service.AuditLogService;
import com.reagryan.online_banking.service.TransactionService;
import com.reagryan.online_banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    private final AuditLogService auditLogService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository, UserService userService, AuditLogService auditLogService) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.auditLogService = auditLogService;
    }

    @Override
    public ApiResponse cashDeposit(Long userId, TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException {
        TransactionResponse response = convertTransactionToResponse(deposit(userId, request));

        MetaDataRequest requestMetaData = new MetaDataRequest();
        requestMetaData.setIp("193.186.4.64");
        requestMetaData.setDevice("HP Laptop");
        requestMetaData.setLocation("Aylesbury");

        AuditLogRequest auditLogRequest = new AuditLogRequest();
        auditLogRequest.setUserId(String.valueOf(userId));
        auditLogRequest.setAction("Deposit");
        auditLogRequest.setMetaDataRequest(requestMetaData);
        auditLogService.submitAuditLog(auditLogRequest);
//        auditLogService.submitAuditLog("DEPOSIT", String.valueOf(userId),  "82.132.232.201", "HP laptop", "Aylesbury, UK");

            return new ApiResponse(false,
                    " Your account has been successfully credited",
                    response);
    }


    @Override
    public ApiResponse cashWithdrawal(Long userId, TransactionRequest request) throws CustomerNotFoundException, InvalidAmountException {

        MetaDataRequest requestMetaData = new MetaDataRequest();
        requestMetaData.setIp("193.186.4.64");
        requestMetaData.setDevice("HP Laptop");
        requestMetaData.setLocation("Aylesbury");

        AuditLogRequest auditLogRequest = new AuditLogRequest();
        auditLogRequest.setUserId(String.valueOf(userId));
        auditLogRequest.setAction("Deposit");
        auditLogRequest.setMetaDataRequest(requestMetaData);
        auditLogService.submitAuditLog(auditLogRequest);
//        auditLogService.submitAuditLog("WITHDRAWAL", String.valueOf(userId),  "82.132.232.201", "HP laptop", "Aylesbury, UK");

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

    @Override
    @Cacheable(value = "depositTransactions", key = "'userId:' + #userId + 'transactionType:' + #transactionType")
    public ApiResponse fetchAllDepositTransactionsByUser(Long userId, String transactionType) throws CustomerNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomerNotFoundException("User not found"));
        List<Transaction> fetchUserDepositTransactions = transactionRepository.findByUserAndDepositTransactionType(user, transactionType);

        return new ApiResponse(false, "Deposit transactions fetched successfully",fetchUserDepositTransactions);
    }

    @Override
    @Cacheable(value = "transferTransactions", key = "'userId:' + #userId + 'transactionType:' + #transactionType")
    public ApiResponse fetchAllTransferTransactionsByUser(Long userId, String transactionType) throws CustomerNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomerNotFoundException("User not found"));
        List<Transaction> fetchUserTransferTransactions = transactionRepository.findByUserAndTransferTransactionType(user, transactionType);

        return new ApiResponse(false, "Transfer transactions fetched successfully", fetchUserTransferTransactions);
    }


    @Override
    @Cacheable(value = "transactionsCache", key = "'page:' + #page + ',size:' + #size + ',sortBy:' + #sortBy + ',direction:' + #direction")
    public ApiResponse<Page<List<TransactionResponse>>> fetchAllTransactions(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Transaction> transactionPage = transactionRepository.findAll(pageable);
        return new ApiResponse(false, "All transactions fetched successfully", transactionPage);
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
