package com.example.BookMyShow.service.serviceImpl;


import com.example.BookMyShow.dto.LoginRequestDto;
import com.example.BookMyShow.dto.SignupRequestDto;
import com.example.BookMyShow.dto.UpdateUserRequestDto;
import com.example.BookMyShow.dto.UserResponseDto;
import com.example.BookMyShow.exception.DuplicateEmailException;
import com.example.BookMyShow.exception.InvalidEmailException;
import com.example.BookMyShow.exception.InvalidPasswordException;
import com.example.BookMyShow.model.User;
import com.example.BookMyShow.repository.UserRepository;
import com.example.BookMyShow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("UserRepositoryImpl")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public UserResponseDto signUp(SignupRequestDto requestDto) {

        //check if the email already exist
        Optional<User> savedUser = userRepository.findByEmail(requestDto.email());
        if (savedUser.isPresent()) {
            throw new DuplicateEmailException("Email already exists");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = SignupRequestDto.toUser(requestDto);
        user.setPassword(encoder.encode(requestDto.password()));
        return UserResponseDto.fromUser(userRepository.save(user));
    }

    @Override
    public UserResponseDto login(LoginRequestDto requestDto) {
        //check if the email already exist
        Optional<User> savedUser = userRepository.findByEmail(requestDto.email());
        if (savedUser.isEmpty()) {
            throw new InvalidEmailException("Given Email does not exist");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(requestDto.password(), savedUser.get().getPassword())) {
            throw new InvalidPasswordException("Password does not match the original password");
        }
        return UserResponseDto.fromUser(savedUser.get());
    }

    @Override
    public UserResponseDto updateUser(UpdateUserRequestDto requestDto) {

        return null;
    }
}
