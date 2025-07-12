package com.ducquy.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking_references")
@Data // Sinh tự động các phương thức getter, setter, toString, equals, hashCode
@AllArgsConstructor // Sinh constructor với tất cả các trường
@NoArgsConstructor // Sinh constructor không tham số
@Builder // Hỗ trợ pattern Builder để tạo đối tượng một cách linh hoạt
public class BookingReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID booking reference

    @Column(unique = true, nullable = false) // Đảm bảo booking reference là duy nhất và không được để trống
    private String referenceNo; // Mã tham chiếu đặt phòng, duy nhất để theo dõi đặt phòng
}
