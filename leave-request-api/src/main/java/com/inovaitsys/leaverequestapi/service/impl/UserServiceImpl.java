package com.inovaitsys.leaverequestapi.service.impl;

import com.inovaitsys.leaverequestapi.dto.LoginResponseDto;
import com.inovaitsys.leaverequestapi.dto.UserDto;
import com.inovaitsys.leaverequestapi.entity.User;
import com.inovaitsys.leaverequestapi.exceptions.ApplicationException;
import com.inovaitsys.leaverequestapi.repository.UserRepository;
import com.inovaitsys.leaverequestapi.service.JwtService;
import com.inovaitsys.leaverequestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDto register(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());

        if (userOptional.isPresent()) {
            throw new ApplicationException("User already exists. Please login");
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User createdUser = userRepository.save(user);

        return new UserDto(createdUser.getId(), createdUser.getUsername(), "*****");
    }

    @Override
    public LoginResponseDto authenticate(UserDto userDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                )
        );

        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new ApplicationException("User not found"));

        String jwtToken = jwtService.generateToken(user);

        return new LoginResponseDto(
                jwtToken,
                jwtService.getExpirationTime(),
                new UserDto(user.getId(), user.getUsername(), "*****")
        );
    }
}
