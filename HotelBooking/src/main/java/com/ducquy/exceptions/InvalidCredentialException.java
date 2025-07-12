package com.ducquy.exceptions;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message) {
        super(message); // Gọi constructor của lớp cha (RuntimeException) với thông điệp lỗi
    }
}
