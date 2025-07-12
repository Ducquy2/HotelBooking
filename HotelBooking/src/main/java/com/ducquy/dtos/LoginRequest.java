package com.ducquy.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotEmpty(message = "Email is required")
    private String email; // Email người dùng để đăng nhập

    @NotEmpty(message = "Password is required")
    private String password; // Mật khẩu người dùng để đăng nhập
}
