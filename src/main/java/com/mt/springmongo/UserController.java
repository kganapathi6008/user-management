package com.mt.springmongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. Handle JSON input (application/json)
    @PostMapping(value = "/save", consumes = "application/json")
    public User saveJson(@RequestBody User user) {
        logger.info("Creating user from JSON: {}", user.getFirstName());
        return userRepository.save(user);
    }

    // 2. Handle form data (application/x-www-form-urlencoded)
    @PostMapping(value = "/save", consumes = "application/x-www-form-urlencoded")
    public User saveForm(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email
    ) {
        logger.info("Creating user from Form: {}", firstName);
        User user = new User(firstName, lastName, email);
        return userRepository.save(user);
    }
}

