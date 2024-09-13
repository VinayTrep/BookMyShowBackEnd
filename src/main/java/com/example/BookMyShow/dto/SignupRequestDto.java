package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.User;

import java.util.ArrayList;

public record SignupRequestDto(String name, String email, String phoneNumber, String password) {

    public static User toUser(SignupRequestDto signupRequestDto) {
        User user = new User();
        user.setEmail(signupRequestDto.email);
        user.setName(signupRequestDto.name);
        user.setPhoneNumber(signupRequestDto.phoneNumber);
        user.setTickets(new ArrayList<>());
        return user;
    }
}
