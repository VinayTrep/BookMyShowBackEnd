package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Payment;

import java.io.Serializable;
import java.util.UUID;

public record PaymentResponseDto(UUID paymentId, UUID ticketId, String referenceId, String timeOfPayment, String paymentStatus,
                                 String paymentMode) implements Serializable {
    public static PaymentResponseDto toDto(Payment payment){
        return new PaymentResponseDto(payment.getId(), payment.getTicket().getId(), payment.getReferenceId(), payment.getPaymentTime().toString(),
                payment.getStatus().name(), payment.getPaymentMode().name());
    }
}
