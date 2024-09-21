package com.example.BookMyShow.model;

import com.example.BookMyShow.model.constants.ShowSeatStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "show_seats")
public class ShowSeat extends BaseModel{

    private int price;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Show show;

    @ManyToOne(fetch = FetchType.EAGER)
    private Seat seat;

}