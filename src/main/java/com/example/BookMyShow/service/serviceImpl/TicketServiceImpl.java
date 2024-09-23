package com.example.BookMyShow.service.serviceImpl;

import com.example.BookMyShow.dto.CreatePaymentRequestDto;
import com.example.BookMyShow.dto.CreateTicketRequestDto;
import com.example.BookMyShow.dto.TicketResponseDto;
import com.example.BookMyShow.exception.ShowNotFoundException;
import com.example.BookMyShow.exception.ShowSeatNotFoundException;
import com.example.BookMyShow.exception.ShowSeatUnavailableException;
import com.example.BookMyShow.exception.TicketNotFoundException;
import com.example.BookMyShow.model.Show;
import com.example.BookMyShow.model.ShowSeat;
import com.example.BookMyShow.model.Ticket;
import com.example.BookMyShow.model.constants.ShowSeatStatus;
import com.example.BookMyShow.model.constants.TicketStatus;
import com.example.BookMyShow.repository.ShowRepository;
import com.example.BookMyShow.repository.ShowSeatRepository;
import com.example.BookMyShow.repository.TicketRepository;
import com.example.BookMyShow.service.PaymentService;
import com.example.BookMyShow.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("TicketServiceImpl")
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final PaymentService paymentService;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, PaymentService paymentService) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.paymentService = paymentService;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TicketResponseDto bookTicket(CreateTicketRequestDto createTicketRequestDto) throws ShowNotFoundException, ShowSeatNotFoundException, ShowSeatUnavailableException {
        Show show = showRepository.findById(createTicketRequestDto.showID()).orElseThrow(() -> new ShowNotFoundException("Show Not Found"));
        int totalCost = 0;
        List<ShowSeat> showSeatList = new ArrayList<>();
        for (UUID showSeatID: createTicketRequestDto.showSeatIDs()) {
            ShowSeat showSeat = showSeatRepository.findById(showSeatID).orElseThrow(() -> new ShowSeatNotFoundException("Show Seat Not Found for id " + showSeatID));
            if (showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
                totalCost += showSeat.getPrice();
                showSeatList.add(showSeat);
            }else{
                throw new ShowSeatUnavailableException("Show Seat Unavailable for id " + showSeatID );
            }
        }
        //populate ticket
        Ticket ticket = CreateTicketRequestDto.fromDto(createTicketRequestDto);
        ticket.setTotalPrice(totalCost);
        ticket.setShow(show);
        ticket.setShowSeats(showSeatList);
        // call payment before you store the ticket
        CreatePaymentRequestDto requestDto = new CreatePaymentRequestDto(ticket);
        paymentService.makePayment(requestDto);
        return TicketResponseDto.toDto(ticketRepository.save(ticket));
    }

    @Override
    public TicketResponseDto getTicketById(UUID ticketID) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketID).orElseThrow(() -> new TicketNotFoundException("Ticket Not Found"));
        return TicketResponseDto.toDto(ticketRepository.save(ticket));
    }

    @Override
    public void cancelTicket(UUID ticketID) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketID).orElseThrow(() -> new TicketNotFoundException("Ticket Not Found"));
        for (ShowSeat showSeat: ticket.getShowSeats()) {
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
        }
        ticketRepository.delete(ticket);
    }
}
