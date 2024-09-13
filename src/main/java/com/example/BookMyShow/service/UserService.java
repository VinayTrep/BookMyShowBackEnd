package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.LoginRequestDto;
import com.example.BookMyShow.dto.SignupRequestDto;
import com.example.BookMyShow.dto.UpdateUserRequestDto;
import com.example.BookMyShow.dto.UserResponseDto;
import com.example.BookMyShow.exception.DuplicateEmailException;
import com.example.BookMyShow.exception.InvalidEmailException;
import com.example.BookMyShow.exception.InvalidPasswordException;

public interface UserService {
     UserResponseDto signUp(SignupRequestDto requestDto) throws DuplicateEmailException;
     UserResponseDto login(LoginRequestDto requestDto) throws InvalidEmailException, InvalidPasswordException;
     UserResponseDto updateUser(UpdateUserRequestDto requestDto) throws  InvalidEmailException;
}
