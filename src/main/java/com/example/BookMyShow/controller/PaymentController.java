package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.PaymentResponseDto;
import com.example.BookMyShow.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{paymentID}")
    public ResponseEntity<PaymentResponseDto> getPayment(@PathVariable("paymentID")UUID paymentID) {
        PaymentResponseDto paymentResponseDto = paymentService.getPayment(paymentID);
        return ResponseEntity.ok(paymentResponseDto);
    }
}
