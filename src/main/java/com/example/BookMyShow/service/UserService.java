package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.*;
import com.example.BookMyShow.exception.DuplicateEmailException;
import com.example.BookMyShow.exception.InvalidEmailException;
import com.example.BookMyShow.exception.InvalidPasswordException;
import com.example.BookMyShow.exception.InvalidUserException;

import java.util.UUID;

public interface UserService {
     UserResponseDto signUp(SignupRequestDto requestDto) throws DuplicateEmailException;
     UserResponseDto login(LoginRequestDto requestDto) throws InvalidEmailException, InvalidPasswordException;
     UserResponseDto updateUser(UUID userId, UpdateUserRequestDto requestDto) throws InvalidUserException;
     void changePassword(UUID userId, ChangePasswordRequestDto requestDto) throws InvalidPasswordException,InvalidUserException;
     void removeUser(UUID userId);
}
