package com.example.BookMyShow.service.serviceImpl;

import com.example.BookMyShow.dto.CreatePaymentRequestDto;
import com.example.BookMyShow.dto.PaymentResponseDto;
import com.example.BookMyShow.exception.PaymentNotFoundException;
import com.example.BookMyShow.model.Payment;
import com.example.BookMyShow.model.ShowSeat;
import com.example.BookMyShow.model.constants.ShowSeatStatus;
import com.example.BookMyShow.model.constants.TicketStatus;
import com.example.BookMyShow.repository.PaymentRepository;
import com.example.BookMyShow.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("PaymentServiceImpl")
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void makePayment(CreatePaymentRequestDto requestDto) {
        Payment payment = CreatePaymentRequestDto.fromDto(requestDto);
        payment.getTicket().setTicketStatus(TicketStatus.BOOKED);
        for(ShowSeat showSeat: requestDto.ticket().getShowSeats()){
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
        }
        paymentRepository.save(payment);
    }

    @Override
    public PaymentResponseDto getPayment(UUID paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
        return PaymentResponseDto.toDto(payment);
    }
}
