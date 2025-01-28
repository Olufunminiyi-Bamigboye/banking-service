package com.reagryan.online_banking.repository;

import com.reagryan.online_banking.dto.response.TransactionResponse;
import com.reagryan.online_banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
