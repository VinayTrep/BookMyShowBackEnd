package com.example.BookMyShow.model;

import com.example.BookMyShow.service.Strategy.PriceStrategyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shows")
public class Show extends BaseModel {
    private Instant startTime;
    private Instant endTime;
    @ManyToOne
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "show", cascade = CascadeType.ALL)
    private List<ShowSeat> showSeats;

    private PriceStrategyType strategyType;
}