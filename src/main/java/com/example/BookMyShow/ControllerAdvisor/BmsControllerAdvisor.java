package com.example.BookMyShow.ControllerAdvisor;

import com.example.BookMyShow.controller.*;
import com.example.BookMyShow.dto.ExceptionResponseDto;
import com.example.BookMyShow.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {CityController.class, TheaterController.class,
        AuditoriumController.class, UserController.class, SeatController.class,
        MovieController.class,ActorController.class,
        ShowController.class,TicketController.class})

public class BmsControllerAdvisor {

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleCityNotFoundException(CityNotFoundException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 404);

        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CityNameEmptyException.class)
    public ResponseEntity<ExceptionResponseDto> handleCityNameEmptyException(CityNameEmptyException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 400);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TheaterNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleTheaterNotFoundException(TheaterNotFoundException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 404);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TheaterAddressEmptyException.class, TheaterNameEmptyException.class,TheaterImageEmptyException.class})
    public ResponseEntity<ExceptionResponseDto> handleTheaterAddressEmptyException(ValidateTheaterException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 400);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidAuditoriumFeature.class,InvalidCapacityException.class,AuditoriumNameEmptyException.class})
    public ResponseEntity<ExceptionResponseDto> handleValidAuditoriumException(ValidateAuditoriumException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 400);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ExceptionResponseDto> handleDuplicateEmailException(DuplicateEmailException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 400);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ExceptionResponseDto> handleInvalidEmailException(InvalidEmailException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 400);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ExceptionResponseDto> handleInvalidPasswordException(InvalidPasswordException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 400);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidEmailFormateException.class,InvalidPasswordFormateException.class})
    public ResponseEntity<ExceptionResponseDto> handleValidateUserControllerException(ValidateUserControllerException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 400);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuditoriumNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleAuditoriumNotFoundException(AuditoriumNotFoundException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 404);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleSeatNotFoundException(SeatNotFoundException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 404);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({SeatExistsException.class})
    public ResponseEntity<ExceptionResponseDto> handleSeatExistsException(SeatExistsException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 400);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({InvalidSeatStatusException.class,InvalidSeatTypeException.class})
    public ResponseEntity<ExceptionResponseDto> handleValidateSeatControllerException(ValidateSeatControllerException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 400);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleMovieNotFoundException(MovieNotFoundException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 404);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ActorNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleActorNotFoundException(ActorNotFoundException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 404);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidMovieFeatureException.class})
    public ResponseEntity<ExceptionResponseDto> handleValidateMovieControllerException(ValidateMovieControllerException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 400);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleTicketNotFoundException(TicketNotFoundException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 404);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShowSeatNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleShowSeatNotFoundException(ShowSeatNotFoundException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 404);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShowSeatUnavailableException.class)
    public ResponseEntity<ExceptionResponseDto> handleShowSeatUnavailableException(ShowSeatUnavailableException ex) {
        ExceptionResponseDto responseDto = new ExceptionResponseDto(ex.getMessage(), 404);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

}
