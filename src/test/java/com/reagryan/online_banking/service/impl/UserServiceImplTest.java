package com.reagryan.online_banking.service.impl;

import com.reagryan.online_banking.dto.request.UserRequestDto;
import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.entity.User;
import com.reagryan.online_banking.exception.CustomerNotFoundException;
import com.reagryan.online_banking.exception.InvalidUserException;
import com.reagryan.online_banking.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class UserServiceImplTest {
    @MockBean
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @Test
    void testCreateUser() throws InvalidUserException {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setFirstName("My firstName");
        userRequestDto.setLastName("My lastName");
        userRequestDto.setPhoneNo("My phoneNo");
        userRequestDto.setGender("male");
        userRequestDto.setEmail("My email");
        userRequestDto.setCreatedAt(LocalDateTime.of(2021, 10, 15, 11, 55));


        User user = new User(1L, "My firstName", "My lastName", "My phoneNo", "male", "My email", LocalDateTime.of(2021, 10, 15, 11, 55));

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        ApiResponse response = userService.createUser(userRequestDto);
        User accountUser = (User) response.getData();

        Assertions.assertNotNull(response.getData());
        Assertions.assertEquals("My firstName", accountUser.getFirstName());
        Assertions.assertEquals("My lastName", accountUser.getLastName());
    }

    @Test
    void testDeleteUserById() throws CustomerNotFoundException {
        User user = new User(1L, "My firstName", "My lastName", "My phoneNo", "male", "My email", LocalDateTime.of(2021, 10, 15, 11, 55));

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Mockito.doNothing().when(userRepository).deleteById(1L);

        ApiResponse response = userService.deleteUserById(1L);

        Assertions.assertEquals("User deleted successfully", response.getMessage());
    }

    @Test
    void testEmptyUserFirstNameOrLastNameShouldThrowException() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setFirstName("");
        userRequestDto.setLastName("");

        Assertions.assertThrows(InvalidUserException.class, () -> userService.createUser(userRequestDto));
    }

    @Test
    void testInvalidIdShouldThrowException() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(CustomerNotFoundException.class, () -> userService.deleteUserById(1L));
    }
}