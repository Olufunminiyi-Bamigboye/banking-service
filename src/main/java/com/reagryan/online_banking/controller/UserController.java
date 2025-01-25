package com.reagryan.online_banking.controller;

import com.reagryan.online_banking.dto.request.UserRequestDto;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.exception.InvalidUserException;
import com.reagryan.online_banking.exception.CustomerNotFoundException;
import com.reagryan.online_banking.service.BankCardService;
import com.reagryan.online_banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BankCardService newBankCardService;

    @PostMapping("users")
    public ResponseEntity<ApiResponse> addUser(@Validated @RequestBody UserRequestDto userRequestDto) throws InvalidUserException {
        return ResponseEntity.ok().body(userService.createUser(userRequestDto));
    }

    @DeleteMapping("users/{userId}")
    public  ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) throws CustomerNotFoundException {
        return  ResponseEntity.ok().body(userService.deleteUserById(userId));
    }

    @PostMapping("users/{userId}/generate")
    public ResponseEntity<ApiResponse> generateNewCard(@PathVariable Long userId) throws CustomerNotFoundException {
        return ResponseEntity.ok().body(newBankCardService.requestNewBankCard(userId));
    }
}
