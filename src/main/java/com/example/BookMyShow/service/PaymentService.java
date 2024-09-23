package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.CreatePaymentRequestDto;
import com.example.BookMyShow.dto.PaymentResponseDto;

import java.util.UUID;

public interface PaymentService {
    void makePayment(CreatePaymentRequestDto requestDto);
    PaymentResponseDto getPayment(UUID paymentId);
}
