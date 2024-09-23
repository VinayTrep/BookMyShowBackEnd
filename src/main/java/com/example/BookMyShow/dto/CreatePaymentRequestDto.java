package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Payment;
import com.example.BookMyShow.model.Ticket;
import com.example.BookMyShow.model.constants.PaymentMode;
import com.example.BookMyShow.model.constants.PaymentStatus;

import java.time.Instant;

public record CreatePaymentRequestDto(Ticket ticket) {

    public static Payment fromDto(CreatePaymentRequestDto createPaymentRequestDto) {
        Payment payment = new Payment();
        payment.setPaymentTime(Instant.now());
        payment.setTicket(createPaymentRequestDto.ticket);
        payment.setPaymentMode(PaymentMode.WALLET);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setAmount(createPaymentRequestDto.ticket.getTotalPrice());
        return payment;
    }
}
