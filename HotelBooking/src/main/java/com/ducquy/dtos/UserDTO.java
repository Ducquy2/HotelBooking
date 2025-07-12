package com.ducquy.dtos;

import com.ducquy.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // Bỏ qua các thuộc tính không được định nghĩa trong DTO khi nhận dữ liệu JSON
@JsonInclude(JsonInclude.Include.NON_NULL) // Chỉ bao gồm các thuộc tính không null trong JSON
public class UserDTO {

    private Long id; // ID người dùng

    private String email; // Email người dùng, duy nhất

    @JsonIgnore // Bỏ qua thuộc tính này khi chuyển đổi sang JSON để bảo mật
    private String password; // Mật khẩu người dùng, được mã hóa

    private String firstName; // Tên người dùng

    private String lastName; // Họ người dùng

    private String phoneNumber; // Số điện thoại người dùng

    private UserRole role; // Vai trò người dùng (ADMIN, CUSTOMER)

    private boolean active; // Trạng thái hoạt động của người dùng (đã kích hoạt hay chưa)

    private LocalDate createdAt; // Ngày tạo người dùng, mặc định là ngày hiện tại

}
