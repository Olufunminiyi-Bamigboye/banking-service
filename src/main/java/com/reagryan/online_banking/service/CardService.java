package com.reagryan.online_banking.service;

import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.exception.CustomerNotFoundException;

public interface CardService {
    ApiResponse requestNewBankCard(Long userId) throws CustomerNotFoundException;
}
