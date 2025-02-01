package com.reagryan.online_banking.service;

import com.reagryan.online_banking.entity.Card;
import com.reagryan.online_banking.entity.User;
import com.reagryan.online_banking.repository.CardRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Slf4j
public class CardExpirationNotificationService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "0 31 0 * * ?") // Runs daily at 6 PM
    public void notifyUsersAboutExpiringCards(){
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(30).with(LocalTime.MAX); // END OF THE DAY  23:59
        log.info("endTime : " + endDate);
        List<Card> expiringCards = cardRepository.findExpiringCards(startDate, endDate);
        log.info("expiringCards : " + expiringCards);

        for (Card card : expiringCards) {
            notificationService.sendNotification(
                    card.getUser().getEmail(),
                    "Your card is expiring soon",
                    "Your card ending in " + card.getCardNumber().substring(card.getCardNumber().length() - 4) +
                            " is expiring on " + card.getExpiresAt() + ". Please request a replacement."
            );

            System.out.println("mail successfully sent out");
            card.setNotified(true);
            cardRepository.save(card);
        }
    }
}
