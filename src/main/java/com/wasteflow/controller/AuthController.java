package com.wasteflow.controller;

import com.wasteflow.dto.request.LoginRequest;
import com.wasteflow.dto.request.SignupRequest;
import com.wasteflow.dto.response.JwtResponse;
import com.wasteflow.dto.response.MessageResponse;
import com.wasteflow.entity.Role;
import com.wasteflow.entity.User;
import com.wasteflow.repository.UserRepository;
import com.wasteflow.security.JwtUtils;
import com.wasteflow.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        return ResponseEntity.ok(new JwtResponse(jwt, 
                                                 userDetails.getId(), 
                                                 userDetails.getNama(), 
                                                 userDetails.getEmail(), 
                                                 role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User();
        user.setNama(signUpRequest.getNama());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setAlamat(signUpRequest.getAlamat());

        String strRole = signUpRequest.getRole();
        Role role;

        if (strRole == null) {
            role = Role.WARGA;
        } else {
            switch (strRole.toLowerCase()) {
            case "admin":
                role = Role.ADMIN;
                break;
            default:
                role = Role.WARGA;
            }
        }

        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
