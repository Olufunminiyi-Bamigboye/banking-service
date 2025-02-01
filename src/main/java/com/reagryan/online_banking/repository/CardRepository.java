package com.reagryan.online_banking.repository;

import com.reagryan.online_banking.entity.Card;
import com.reagryan.online_banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT COUNT(c) FROM Card c WHERE c.user = :user and c.createdAt BETWEEN :startDay and :endDay")
    long countCardRequestByUserAndDate(User user, LocalDateTime startDay, LocalDateTime endDay);




//    @Query("SELECT c FROM Card c WHERE c.expiresAt BETWEEN :startDate and :endDate AND c.notified = false")
//    List<Card> findExpiringCards(LocalDateTime startDate, LocalDateTime endDate);

//modified query to eagerly load from the db
    @Query("SELECT c FROM Card c JOIN FETCH c.user WHERE c.expiresAt BETWEEN :startDate AND :endDate AND c.notified = false")
    List<Card> findExpiringCards(LocalDateTime startDate, LocalDateTime endDate);
}
