package com.example.BookMyShow.ControllerAdvisor;

import com.example.BookMyShow.controller.AuditoriumController;
import com.example.BookMyShow.controller.CityController;
import com.example.BookMyShow.controller.TheaterController;
import com.example.BookMyShow.dto.ExceptionResponseDto;
import com.example.BookMyShow.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {CityController.class, TheaterController.class, AuditoriumController.class})
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
}
