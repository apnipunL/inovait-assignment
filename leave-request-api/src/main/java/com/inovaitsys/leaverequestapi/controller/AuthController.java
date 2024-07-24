package com.inovaitsys.leaverequestapi.controller;

import com.inovaitsys.leaverequestapi.dto.LoginResponseDto;
import com.inovaitsys.leaverequestapi.dto.UserDto;
import com.inovaitsys.leaverequestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.register(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody UserDto userDto) {
        LoginResponseDto loginResponseDto = userService.authenticate(userDto);
        return ResponseEntity.ok(loginResponseDto);
    }

}
