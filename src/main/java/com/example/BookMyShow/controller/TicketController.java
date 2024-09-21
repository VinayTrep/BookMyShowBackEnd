package com.example.BookMyShow.controller;

import com.example.BookMyShow.dto.CreateTicketRequestDto;
import com.example.BookMyShow.dto.TicketResponseDto;
import com.example.BookMyShow.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/book")
    public ResponseEntity<TicketResponseDto> bookTicket(@RequestBody CreateTicketRequestDto requestDto) {
        return ResponseEntity.ok(ticketService.bookTicket(requestDto));
    }
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDto> getTicket(@PathVariable("ticketId") UUID ticketId) {
        return ResponseEntity.ok(ticketService.getTicketById(ticketId));
    }

    @DeleteMapping("/{ticketID}")
    public ResponseEntity<String> deleteTicket(@PathVariable("ticketID") UUID ticketId) {
        ticketService.cancelTicket(ticketId);
        return ResponseEntity.ok("Ticket canceled successfully");
    }
}
