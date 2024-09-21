package com.example.BookMyShow.dto;

import com.example.BookMyShow.model.Ticket;
import com.example.BookMyShow.model.constants.TicketStatus;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record CreateTicketRequestDto(List<UUID> showSeatIDs, UUID showID) {

    public static Ticket fromDto(CreateTicketRequestDto createTicketRequestDto) {
        Ticket ticket = new Ticket();
        ticket.setTicketStatus(TicketStatus.IN_PROGRESS);
        ticket.setTimeOfBooking(Instant.now());
        return ticket;
    }
}
