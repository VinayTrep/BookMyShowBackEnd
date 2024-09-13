package com.example.BookMyShow.model;

import com.example.BookMyShow.model.constants.AuditoriumFeatures;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "auditoriums")
public class Auditorium extends BaseModel{
    private String auditoriumName;
    private int capacity;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "auditorium")
    private List<Show> shows;

    @OneToMany(mappedBy = "auditorium",fetch = FetchType.LAZY)
    private List<Seat> seats;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<AuditoriumFeatures> auditoriumFeatures;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;
}