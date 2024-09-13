package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.*;
import com.example.BookMyShow.exception.InvalidEmailFormateException;
import com.example.BookMyShow.exception.InvalidPasswordFormateException;
import com.example.BookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody SignupRequestDto requestDto) {
        validateEmail(requestDto.email());
        validatePassword(requestDto.password());
        return ResponseEntity.ok(userService.signUp(requestDto));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok(userService.login(requestDto));
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserResponseDto> update(@PathVariable UUID userId, @RequestBody UpdateUserRequestDto requestDto){
        validateEmail(requestDto.email());
        return ResponseEntity.ok(userService.updateUser(userId,requestDto));
    }

    @PutMapping("/changepassword/{userId}")
    public ResponseEntity<String> changePassword(@PathVariable UUID userId, @RequestBody ChangePasswordRequestDto requestDto){
        validatePassword(requestDto.oldPassword());
        validatePassword(requestDto.newPassword());
        userService.changePassword(userId,requestDto);
        return ResponseEntity.ok("Password changed successfully");
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<String> remove(@PathVariable UUID userId) {
        userService.removeUser(userId);
        return ResponseEntity.ok("User Removed successfully");
    }


    private void validateEmail(String email) throws InvalidEmailFormateException {
        String emailRegex = "^[A-Za-z0-9+_. -]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (! matcher.matches()){
            throw new InvalidEmailFormateException("Email format is not correct");
        }
    }
    private void validatePassword(String password) {
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z0-9@$!%*#?&]{8,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);

        if (!passwordMatcher.matches()){
            throw new InvalidPasswordFormateException("Password dose not follow required format");
        }
    }

}
