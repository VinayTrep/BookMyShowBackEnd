package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Ticket;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record TicketResponseDto(UUID ticketId, String timeOfBooking, List<String> showSeat, TicketShowResponseDto show, int totalPrice, String ticketStatus) {

    public static TicketResponseDto toDto(Ticket ticket) {
        return new TicketResponseDto( ticket.getId(), ticket.getTimeOfBooking().toString(),
                ticket.getShowSeats().stream().map(showSeat1 -> showSeat1.getSeat().getSeatNumber()).collect(Collectors.toList()),
                TicketShowResponseDto.toDto(ticket.getShow()), ticket.getTotalPrice(), ticket.getTicketStatus().toString());
    }
}
