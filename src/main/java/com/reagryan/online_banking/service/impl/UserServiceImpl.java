package com.reagryan.online_banking.service.impl;

import com.reagryan.online_banking.dto.request.UserRequestDto;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.entity.User;
import com.reagryan.online_banking.exception.InvalidUserException;
import com.reagryan.online_banking.repository.UserRepository;
import com.reagryan.online_banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public ApiResponse createUser(UserRequestDto userRequestDto) throws InvalidUserException {
        if(userRequestDto.getFirstName() == null && userRequestDto.getLastName() == null ||
            userRequestDto.getFirstName().isEmpty() && userRequestDto.getLastName().isEmpty()){
            throw new InvalidUserException("Firstname and/or lastname cannot be empty or null");
        }
        User user = new User(userRequestDto.getFirstName(), userRequestDto.getLastName(), userRequestDto.getPhoneNo(), userRequestDto.getGender(), userRequestDto.getEmail(), LocalDateTime.now());
        userRepository.save(user);

        ApiResponse apiResponse = new ApiResponse(false, "User created successfully", user);
        return apiResponse;
    }

    @Override
    public ApiResponse deleteUserById(Long userId) {
        Optional<User> userToBeDeleted = userRepository.findById(userId);
        if(userToBeDeleted.isPresent()){
            User user = userToBeDeleted.get();
            userRepository.delete(user);
            return new ApiResponse(false,"User deleted successfully", null);
        }else{
            throw new RuntimeException("User with ID " + userId + " not found");
        }
    }


}
