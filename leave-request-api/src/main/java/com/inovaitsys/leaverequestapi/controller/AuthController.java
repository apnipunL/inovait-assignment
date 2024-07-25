package com.inovaitsys.leaverequestapi.controller;

import com.inovaitsys.leaverequestapi.dto.LoginResponseDto;
import com.inovaitsys.leaverequestapi.dto.UserDto;
import com.inovaitsys.leaverequestapi.entity.User;
import com.inovaitsys.leaverequestapi.service.JwtService;
import com.inovaitsys.leaverequestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        log.info("AuthController::register -> {}", userDto);

        UserDto createdUser = userService.register(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserDto userDto) {
        log.info("AuthController::login -> {}", userDto);

        LoginResponseDto loginResponseDto = userService.authenticate(userDto);
        return ResponseEntity.ok(loginResponseDto);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser() {
        User currentUser = jwtService.getCurrentUser();

        log.info("AuthController::getCurrentUser -> {}", currentUser.getUsername());

        return ResponseEntity.ok(new UserDto(currentUser.getId(), currentUser.getUsername(), "*****"));
    }

}
