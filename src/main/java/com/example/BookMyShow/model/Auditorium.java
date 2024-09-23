package com.example.BookMyShow.model;

import com.example.BookMyShow.model.constants.AuditoriumFeatures;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "auditoriums")
public class Auditorium extends BaseModel {
    private String auditoriumName;
    private int capacity;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "auditorium", cascade = CascadeType.ALL)
    private List<Show> shows;

    @OneToMany(mappedBy = "auditorium",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Seat> seats;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<AuditoriumFeatures> auditoriumFeatures;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;
}