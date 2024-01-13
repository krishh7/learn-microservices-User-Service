package com.lcwd.user.service.controller;

import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    private int retryCount = 1; //for Retry

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    //single user get
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelSlow", fallbackMethod = "ratingHotelFallback") //for Retry
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
//        logger.info("Retry count:{}", retryCount); //for retry
//        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //creating fallback method for circuit breaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception e) {
        logger.info("Fallback is executed because service is down: ", e.getMessage());
        User user = User.builder()
                .email("dummyuser@gmail.com")
                .name("dummy name")
                .userId("dummy user id")
                .about("This is a dummy user because some service is down.")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
}
