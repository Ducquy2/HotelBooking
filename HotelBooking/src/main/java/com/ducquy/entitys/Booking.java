package com.ducquy.entitys;

import com.ducquy.enums.BookingStatus;
import com.ducquy.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID đặt phòng

    @ManyToOne(cascade =  CascadeType.REMOVE) // Nhiều đặt phòng có thể liên kết với một phòng
    @JoinColumn(name = "user_id") // Liên kết với bảng users thông qua user_id
    private User user; // Người dùng đã đặt phòng, liên kết với bảng users

    @ManyToOne(cascade = CascadeType.REMOVE) // Nhiều đặt phòng có thể liên kết với một phòng
    @JoinColumn(name = "room_id") // Liên kết với bảng rooms thông qua room_id
    private Room room; // Phòng đã được đặt, liên kết với bảng rooms

    @Enumerated(EnumType.STRING) // Lưu trữ giá trị enum dưới dạng chuỗi
    private PaymentStatus paymentStatus; // Trạng thái thanh toán (PENDING, COMPLETED, FAILED)

    private LocalDate checkInDate; // Ngày nhận phòng
    private LocalDate checkOutDate; // Ngày trả phòng

    private BigDecimal totalPrice; // Tổng giá tiền cho đặt phòng, tính theo số đêm và giá mỗi đêm của phòng
    private String bookingReference; // Mã tham chiếu đặt phòng, duy nhất để theo dõi đặt phòng
    private LocalDateTime createdAt; // Ngày tạo đặt phòng, mặc định là ngày hiện tại

    @Enumerated(EnumType.STRING) // Lưu trữ giá trị enum dưới dạng chuỗi
    private BookingStatus bookingStatus; // Trạng thái đặt phòng (PENDING, CONFIRMED, CANCELED)


}
