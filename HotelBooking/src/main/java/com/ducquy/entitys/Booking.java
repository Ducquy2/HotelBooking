package com.ducquy.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data // Sinh tự động các phương thức getter, setter, toString, equals, hashCode
@AllArgsConstructor // Sinh constructor với tất cả các trường
@NoArgsConstructor // Sinh constructor không tham số
@Builder // Hỗ trợ pattern Builder để tạo đối tượng một cách linh hoạt
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID đặt phòng, tự động tăng

    @ManyToOne(cascade =  CascadeType.REMOVE) // Nhiều đặt phòng có thể liên kết với một phòng
    @JoinColumn(name = "user_id") // Liên kết với bảng users thông qua user_id
    private User user; // Người dùng đã đặt phòng, liên kết với bảng users

    @ManyToOne(cascade = CascadeType.REMOVE) // Nhiều đặt phòng có thể liên kết với một phòng
    @JoinColumn(name = "room_id") // Liên kết với bảng rooms thông qua room_id
    private Room room; // Phòng đã được đặt, liên kết với bảng rooms

}
