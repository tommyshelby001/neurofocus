package com.neurofocus.neurofocus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    // 🔐 REGISTER USER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepo.save(user);
    }

    // 🔐 LOGIN USER
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existingUser = userRepo.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "LOGIN_SUCCESS:" + existingUser.getId();
        }

        return "LOGIN_FAILED";
    }
}