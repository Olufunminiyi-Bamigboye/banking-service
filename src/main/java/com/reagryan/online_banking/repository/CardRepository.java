package com.reagryan.online_banking.repository;

import com.reagryan.online_banking.entity.Card;
import com.reagryan.online_banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT COUNT(c) FROM Card c WHERE c.user = :user and c.createdAt BETWEEN :startDay and :endDay")
    long countCardRequestByUserAndDate(User user, LocalDateTime startDay, LocalDateTime endDay);

    @Query("SELECT COUNT(c) FROM Card c WHERE c.user = :user")
    long countCardRequestByUser(User user);
}
