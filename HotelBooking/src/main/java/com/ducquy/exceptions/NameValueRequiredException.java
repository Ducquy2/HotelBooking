package com.ducquy.exceptions;

public class NameValueRequiredException extends RuntimeException{
    public NameValueRequiredException(String message) {
        super(message); // Gọi constructor của lớp cha (RuntimeException) với thông điệp lỗi
    }
}
