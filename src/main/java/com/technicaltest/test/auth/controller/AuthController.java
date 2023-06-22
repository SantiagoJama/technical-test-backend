package com.technicaltest.test.auth.controller;

import com.technicaltest.test.auth.models.dto.JwtDto;
import com.technicaltest.test.auth.models.dto.Login;
import com.technicaltest.test.auth.models.dto.NewUser;
import com.technicaltest.test.auth.services.UserService;
import com.technicaltest.test.handler.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> save(@Valid @RequestBody NewUser newUser) throws BadRequestException {
        userService.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PostMapping("/login")
    public ResponseEntity<JwtDto> save(@Valid @RequestBody Login login) {
        JwtDto jwtDto = userService.login(login);
        return ResponseEntity.ok(jwtDto);
    }
}
