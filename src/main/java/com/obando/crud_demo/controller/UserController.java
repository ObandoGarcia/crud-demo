package com.obando.crud_demo.controller;

import com.obando.crud_demo.model.User;
import com.obando.crud_demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> list(){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(user));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        user.setAdmin(false);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.save(user));
    }
}
