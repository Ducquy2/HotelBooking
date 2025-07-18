package com.ducquy.exceptions;


import com.ducquy.dtos.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Annotation này cho phép xử lý các ngoại lệ toàn cục trong ứng dụng Spring
public class GlobalExceptionHandler {

    // Xử lý ngoại lệ NotFoundException
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleAllUnknownException(Exception e) {
        Response response = Response.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(Exception e) {
        Response response = Response.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameValueRequiredException.class)
    public ResponseEntity<Response> handleNameValueRequiredException(Exception e) {
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<Response> handleInvalidCredentialException(Exception e) {
        Response response = Response.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBookingStateAndDateException.class)
    public ResponseEntity<Response> handleInvalidBookingStateAndDateException(Exception e) {
        Response response = Response.builder()
                .status(HttpStatus.BAD_GATEWAY.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }
}
