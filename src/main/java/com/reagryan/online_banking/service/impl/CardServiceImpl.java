package com.reagryan.online_banking.service.impl;

import com.reagryan.online_banking.dto.request.CardRequest;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.dto.response.CardDto;
import com.reagryan.online_banking.dto.response.CardResponse;
import com.reagryan.online_banking.entity.Card;
import com.reagryan.online_banking.entity.User;
import com.reagryan.online_banking.exception.CustomerNotFoundException;
import com.reagryan.online_banking.repository.CardRepository;
import com.reagryan.online_banking.repository.UserRepository;
import com.reagryan.online_banking.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@Slf4j
public class CardServiceImpl implements CardService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${reagandynamiccards.generate.new.atm}")
    private String newBankCardUrl;

    @Override
    public ApiResponse requestNewBankCard(Long userId) throws CustomerNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomerNotFoundException("User not found"));
        LocalDateTime startDay = LocalDateTime.now().with(LocalTime.MIN); //MIDNIGHT 00:00
        LocalDateTime endDay = LocalDateTime.now().with(LocalTime.MAX); // END OF THE DAY  23:59
        long countCardRequestToday = cardRepository.countCardRequestByUserAndDate(user, startDay, endDay);
        log.info("Total card is " + countCardRequestToday);
        if (countCardRequestToday > 1) {
            throw new IllegalArgumentException("You cannot request more than 2 new bank cards");
        }

        CardRequest bankCardRequest = mapUserToBankCardRequest(user);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<CardRequest> request = new HttpEntity<>(bankCardRequest, headers);
        ResponseEntity<CardResponse> response = restTemplate.exchange(newBankCardUrl, HttpMethod.POST, request, CardResponse.class);

        System.out.println("API Response: " + response.getBody());

        if(response.getStatusCode().is2xxSuccessful()) {
//            response.getBody().getData().getCardDto()
            cardRepository.save(convertCardDtoToCard(response.getBody().getData().getCardDto(), user));
        }

        return new ApiResponse(false, "Successfully generated new bank card", response.getBody().getData());
    }

    private Card convertCardDtoToCard(CardDto cardDto, User user) {
        Card generatedCard = new Card(
                cardDto.getCardNumber(),
                cardDto.getCvv(),
                cardDto.getCardType(),
                cardDto.getExpiresAt(),
                LocalDateTime.now(),
                user,
                false
        );
        return generatedCard;
    }

    private CardRequest mapUserToBankCardRequest(User user) {
        CardRequest bankCardRequest = new CardRequest(
                user.getFirstName(),
                user.getLastName(),
                user.getAcctNo(),
                user.getEmail(),
                user.getAddress(),
                LocalDateTime.now()
        );
        return bankCardRequest;
    }

}
