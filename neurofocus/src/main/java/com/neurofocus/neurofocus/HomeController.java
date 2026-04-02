package com.neurofocus.neurofocus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    // ADD USER
    @GetMapping("/add-user")
    public String addUserEasy() {

        User user = new User();
        user.setName("Ayush");
        user.setEmail("ayush@gmail.com");
        user.setPassword("1234");

        userRepository.save(user);

        return "User Saved ✅";
    }

    // GET ALL USERS
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}