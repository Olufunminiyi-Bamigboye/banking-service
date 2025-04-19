package com.reagryan.online_banking.controller;

import com.reagryan.online_banking.dto.request.LoginRequest;
import com.reagryan.online_banking.dto.request.UserRequestDto;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.exception.InvalidUserException;
import com.reagryan.online_banking.exception.CustomerNotFoundException;
import com.reagryan.online_banking.service.CardService;
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
    private CardService newBankCardService;

    @PostMapping("users")
    public ResponseEntity<ApiResponse> addUser(@Validated @RequestBody UserRequestDto userRequestDto) throws InvalidUserException {
        return ResponseEntity.ok().body(userService.createUser(userRequestDto));
    }

    @PostMapping("auth/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody LoginRequest loginRequest)  {
        return ResponseEntity.ok().body(userService.loginRequest(loginRequest));
    }

    @DeleteMapping("users/{userId}")
    public  ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) throws CustomerNotFoundException {
        return  ResponseEntity.ok().body(userService.deleteUserById(userId));
    }

    @PostMapping("users/{userId}/generate")
    public ResponseEntity<ApiResponse> generateNewCard(@PathVariable Long userId) throws CustomerNotFoundException {
        return ResponseEntity.ok().body(newBankCardService.requestNewBankCard(userId));
    }

    @GetMapping("users/welcome")
    public ResponseEntity<String> welcomeUsers() {
        return ResponseEntity.ok().body("WELCOME TO OUR ONLINE BANKING SYSTEM");
    }
}
