package com.inovaitsys.leaverequestapi.service;

import com.inovaitsys.leaverequestapi.dto.LoginResponseDto;
import com.inovaitsys.leaverequestapi.dto.UserDto;

public interface UserService {

    UserDto register(UserDto userDto);

    LoginResponseDto authenticate(UserDto userDto);

}
