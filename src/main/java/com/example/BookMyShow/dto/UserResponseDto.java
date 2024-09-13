package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.User;

import java.util.UUID;

public record UserResponseDto(UUID userId,String name, String email, String phoneNumber) {

    public static UserResponseDto fromUser(User user) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getPhoneNumber());
    }
}
