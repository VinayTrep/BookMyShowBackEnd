package com.example.BookMyShow.service.Strategy;

import com.example.BookMyShow.model.Seat;
import com.example.BookMyShow.model.Show;
import com.example.BookMyShow.model.ShowSeat;

import java.util.List;

public interface PriceGenerationStrategy {

    List<ShowSeat> generatePrice(Show show, List<Seat> seats);
}
