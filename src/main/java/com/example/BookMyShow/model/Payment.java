package com.example.BookMyShow.model;

import com.example.BookMyShow.model.constants.PaymentMode;
import com.example.BookMyShow.model.constants.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity(name = "payments")
public class Payment extends BaseModel{
    @ManyToOne
    private Ticket ticket;
    private String referenceId;
    private Instant paymentTime;
    private int amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;
}
