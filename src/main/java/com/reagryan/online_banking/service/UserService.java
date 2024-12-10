package com.reagryan.online_banking.service;

import com.reagryan.online_banking.dto.request.UserRequestDto;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.exception.InvalidUserException;

public interface UserService {
    ApiResponse createUser(UserRequestDto userRequestDto) throws InvalidUserException;
    ApiResponse deleteUserById(Long userId);
}
