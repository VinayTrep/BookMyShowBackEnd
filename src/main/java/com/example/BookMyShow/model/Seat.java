package com.example.BookMyShow.model;

import com.example.BookMyShow.model.constants.SeatStatus;
import com.example.BookMyShow.model.constants.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "seats")
public class Seat extends BaseModel {
    private String SeatNumber;
    private int columnNumber;
    private int rowsNumber;
    @Enumerated(EnumType.ORDINAL)
    private SeatType SeatType;
    @Enumerated(EnumType.ORDINAL)
    private SeatStatus seatStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

}