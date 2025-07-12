package com.ducquy.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message); // Gọi constructor của lớp cha (RuntimeException) với thông điệp lỗi
    }
}
