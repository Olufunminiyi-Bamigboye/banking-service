package com.reagryan.online_banking.service.impl;

import com.reagryan.online_banking.dto.request.LoginRequest;
import com.reagryan.online_banking.dto.request.UserRequestDto;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.dto.response.LoginResponse;
import com.reagryan.online_banking.entity.User;
import com.reagryan.online_banking.exception.InvalidUserException;
import com.reagryan.online_banking.exception.CustomerNotFoundException;
import com.reagryan.online_banking.repository.UserRepository;
import com.reagryan.online_banking.security.JwtTokenProvider;
import com.reagryan.online_banking.security.UserPrincipal;
import com.reagryan.online_banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public ApiResponse createUser(UserRequestDto userRequestDto) throws InvalidUserException {
        if (userRequestDto.getFirstName() == null && userRequestDto.getLastName() == null ||
                userRequestDto.getFirstName().isEmpty() && userRequestDto.getLastName().isEmpty()) {
            throw new InvalidUserException("Firstname and/or lastname cannot be empty or null");
        }
        User user = new User(userRequestDto.getFirstName(),
                userRequestDto.getLastName(),
                userRequestDto.getPhoneNo(),
                userRequestDto.getGender(),
                userRequestDto.getEmail(),
                passwordEncoder.encode(userRequestDto.getPassword()),
                userRequestDto.getAddress(), LocalDateTime.now());
        userRepository.save(user);

        ApiResponse apiResponse = new ApiResponse(false, "User created successfully", user);
        return apiResponse;
    }

    @Override
    public ApiResponse loginRequest(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                new RuntimeException("User not found"));
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
            Optional<String> role = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .findFirst();

            final String token = jwtTokenProvider.generateToken(authentication);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setFirstName(user.getFirstName());
            loginResponse.setLastName(user.getLastName());
            loginResponse.setPhoneNo(user.getPhoneNo());
            loginResponse.setGender(user.getGender());
            loginResponse.setEmail(user.getEmail());
            loginResponse.setToken(token);
            loginResponse.setRole(role.get());

            return new ApiResponse(false, "User logged in successfully", loginResponse);


        } catch (AuthenticationException e) {
           throw new RuntimeException("Authentication failed", e);
        }
}

@Override
public ApiResponse deleteUserById(Long userId) throws CustomerNotFoundException {
    Optional<User> userToBeDeleted = userRepository.findById(userId);
    if (userToBeDeleted.isPresent()) {
        User user = userToBeDeleted.get();
        userRepository.delete(user);
        return new ApiResponse(false, "User deleted successfully", null);
    } else {
        throw new CustomerNotFoundException("User with ID " + userId + " not found");
    }
}
}
