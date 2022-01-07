package com.lwdevelop.backend.controller;

import com.lwdevelop.backend.models.EType;
import com.lwdevelop.backend.models.Type;
import com.lwdevelop.backend.models.User;
import com.lwdevelop.backend.payload.request.LoginRequest;
import com.lwdevelop.backend.payload.request.SignupRequest;
import com.lwdevelop.backend.payload.request.UpdateUserRequest;
import com.lwdevelop.backend.payload.response.JwtResponse;
import com.lwdevelop.backend.payload.response.MessageResponse;
import com.lwdevelop.backend.payload.response.UserResponse;
import com.lwdevelop.backend.security.jwt.JwtUtils;
import com.lwdevelop.backend.security.services.UserDetailsImpl;
import com.lwdevelop.backend.service.TypeService;
import com.lwdevelop.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class AuthController {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    TypeService typeService;

    @PostMapping
    public ResponseEntity<?> signupUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getPhone()
        );

        Set<String> strtypes = signUpRequest.getType();
        Set<Type> types = new HashSet<>();

        if (strtypes == null) {
            Type buyerType = typeService.findByName(EType.ROLE_BUYER)
                    .orElseThrow(() -> new RuntimeException("Error: Type is not found."));
            types.add(buyerType);
        } else {
            strtypes.forEach(role -> {
                switch (role) {
                    case "seller":
                        Type sellerType = typeService.findByName(EType.ROLE_SELLER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        types.add(sellerType);

                        break;
                    default:
                        Type buyerType = typeService.findByName(EType.ROLE_BUYER)
                                .orElseThrow(() -> new RuntimeException("Error: Type is not found."));
                        types.add(buyerType);
                }
            });
        }

        user.setTypes(types);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("Your account has been successfully created!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId()));
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER')")
    public ResponseEntity<?> getSelfData(Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new UserResponse(userDetails.getId(),
                userDetails.getName(),
                userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList()),
                userDetails.getEmail(),
                userDetails.getPhone()));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('BUYER') or hasRole('SELLER')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {

        User user = userService.getUser(id)
                .orElseThrow(() -> new RuntimeException("User Not Found with id: " + id));
        user.setName(updateUserRequest.getName());
        user.setPhone(updateUserRequest.getPhone());

        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("Your account has been successfully updated!"));
    }
}
