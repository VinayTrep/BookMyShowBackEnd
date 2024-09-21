package com.example.BookMyShow.service;

import com.example.BookMyShow.dto.CreateTicketRequestDto;
import com.example.BookMyShow.dto.TicketResponseDto;
import com.example.BookMyShow.exception.ShowNotFoundException;
import com.example.BookMyShow.exception.ShowSeatNotFoundException;
import com.example.BookMyShow.exception.ShowSeatUnavailableException;
import com.example.BookMyShow.exception.TicketNotFoundException;

import java.util.UUID;

public interface TicketService {
    TicketResponseDto bookTicket(CreateTicketRequestDto createTicketRequestDto) throws ShowNotFoundException, ShowSeatNotFoundException, ShowSeatUnavailableException;
    TicketResponseDto getTicketById(UUID ticketID) throws TicketNotFoundException;
    void cancelTicket(UUID ticketID) throws TicketNotFoundException;
}
