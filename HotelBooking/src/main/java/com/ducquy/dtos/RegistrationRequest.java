package com.ducquy.dtos;

import com.ducquy.enums.UserRole;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotEmpty(message = "Email is required")
    private String firstName; // Tên người dùng

    @NotEmpty(message = "Last name is required")
    private String lastName; // Họ người dùng

    @NotEmpty(message = "Email is required")
    private String email; // Email người dùng, duy nhất

    @NotEmpty(message = "Phone number is required")
    private String phoneNumber; // Số điện thoại người dùng

    private UserRole role; // Vai trò người dùng (ADMIN, CUSTOMER), mặc định là CUSTOMER

    @NotEmpty(message = "Password is required")
    private String password; // Mật khẩu người dùng, được mã hóa


}
