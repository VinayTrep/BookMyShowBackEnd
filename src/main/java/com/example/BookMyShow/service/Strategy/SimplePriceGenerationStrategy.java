package com.example.BookMyShow.service.Strategy;

import com.example.BookMyShow.model.Seat;
import com.example.BookMyShow.model.Show;
import com.example.BookMyShow.model.ShowSeat;
import com.example.BookMyShow.model.constants.SeatStatus;
import com.example.BookMyShow.model.constants.SeatType;
import com.example.BookMyShow.model.constants.ShowSeatStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("SimplePriceGenerationStrategy")
public class SimplePriceGenerationStrategy implements PriceGenerationStrategy {

    @Override
    public List<ShowSeat> generatePrice(Show show, List<Seat> seats) {
        List<ShowSeat> showSeats = new ArrayList<ShowSeat>();
        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(show);
            showSeat.setSeat(seat);

            if (seat.getSeatStatus().equals(SeatStatus.AVAILABLE)){
                showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            }else {
                showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            }
            showSeat.setPrice(getPrice(seat.getSeatType()));
            showSeats.add(showSeat);
        }
        return showSeats;
    }

    private int getPrice(SeatType status){
        return switch (status){
            case CLUB -> 100;
            case GOLD -> 200;
            case PLATINUM -> 250;
            case DIAMOND -> 300;
        };
    }
}
