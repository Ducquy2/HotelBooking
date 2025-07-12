package com.ducquy.entitys;

import com.ducquy.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID người dùng

    @NotBlank(message = "Email is required") // Kiểm tra trường này không được để trống
    @Column(unique = true) // Đảm bảo email là duy nhất trong bảng
    private String email; // Email người dùng, duy nhất

    @NotBlank(message = "Password is required")
    private String password; // Mật khẩu người dùng, được mã hóa
    private String firstName; // Tên người dùng
    private String lastName; // Họ người dùng

    @NotBlank(message = "Phone number is required")
    @Column(name = "phone_Number") // Đảm bảo số điện thoại là duy nhất trong bảng
    private String phoneNumber; // Số điện thoại người dùng

    @Enumerated(EnumType.STRING) // Lưu trữ giá trị enum dưới dạng chuỗi
    private UserRole role; // Vai trò người dùng (ADMIN, CUSTOMER)

    private boolean active; // Trạng thái hoạt động của người dùng (đã kích hoạt hay chưa)

    private final LocalDate createdAt = LocalDate.now(); // Ngày tạo người dùng, mặc định là ngày hiện tại

}
