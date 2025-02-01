package com.reagryan.online_banking.repository;

import com.reagryan.online_banking.entity.Transaction;
import com.reagryan.online_banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.user = :user and t.transactionType = :transactionType")
    List<Transaction> findByUserAndTransactionType(User user, String transactionType);
}
