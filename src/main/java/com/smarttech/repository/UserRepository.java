package com.smarttech.repository;

import com.smarttech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhone(String phone);

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email IN :emails")
    List<User> findByEmails(List<String> emails);
}
