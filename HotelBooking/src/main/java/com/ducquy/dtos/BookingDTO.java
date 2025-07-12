package com.ducquy.dtos;

import com.ducquy.entitys.Room;
import com.ducquy.entitys.User;
import com.ducquy.enums.BookingStatus;
import com.ducquy.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // Bỏ qua các thuộc tính không được định nghĩa trong DTO khi nhận dữ liệu JSON
@JsonInclude(JsonInclude.Include.NON_NULL) // Chỉ bao gồm các thuộc tính không null trong JSON
public class BookingDTO {

    private Long id; // ID đặt phòng

    private UserDTO user; // Người dùng đã đặt phòng, liên kết với bảng users

    private RoomDTO room; // Phòng đã được đặt, liên kết với bảng rooms

    private PaymentStatus paymentStatus; // Trạng thái thanh toán (PENDING, COMPLETED, FAILED)

    private LocalDate checkInDate; // Ngày nhận phòng
    private LocalDate checkOutDate; // Ngày trả phòng

    private BigDecimal totalPrice; // Tổng giá tiền cho đặt phòng, tính theo số đêm và giá mỗi đêm của phòng
    private String bookingReference; // Mã tham chiếu đặt phòng, duy nhất để theo dõi đặt phòng
    private LocalDateTime createdAt; // Ngày tạo đặt phòng, mặc định là ngày hiện tại

    private BookingStatus bookingStatus; // Trạng thái đặt phòng (PENDING, CONFIRMED, CANCELED)
}
