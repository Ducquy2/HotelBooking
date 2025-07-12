package com.ducquy.exceptions;

public class InvalidBookingStateAndDateException extends RuntimeException{
    public InvalidBookingStateAndDateException(String message) {
        super(message); // Gọi constructor của lớp cha (RuntimeException) với thông điệp lỗi
    }
}
