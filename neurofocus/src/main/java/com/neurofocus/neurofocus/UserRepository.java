package com.neurofocus.neurofocus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 🔐 Find user by email (for login)
    User findByEmail(String email);

}