package com.example.BookMyShow.model;

import com.example.BookMyShow.model.constants.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity(name = "tickets")
@Setter
@Getter
public class Ticket extends BaseModel {
    private Instant timeOfBooking;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ShowSeat> showSeats;
    @ManyToOne
    private Show show;
    private int totalPrice;
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;
}
