package com.reagryan.online_banking.repository;


import com.reagryan.online_banking.dto.response.ApiResponse;
import com.reagryan.online_banking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
