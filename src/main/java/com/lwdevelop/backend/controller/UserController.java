// receive the request from frontend, and response to frontend
package com.lwdevelop.backend.controller;

import com.lwdevelop.backend.models.User;
import com.lwdevelop.backend.payload.request.UserRequest;
import com.lwdevelop.backend.payload.response.MessageResponse;
import com.lwdevelop.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = new User(userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getPassword(),
                userRequest.getPhone(),
                "Buyer"
        );

        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("Your product has been successfully created!"));

    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = new ArrayList<>();
        users = userService.getAllUsers();

        return ResponseEntity.ok(users);

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        User user = userService.getUser(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found with id: " + id));

        return ResponseEntity.ok(user);
    }
}

