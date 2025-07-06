package com.ducquy.entitys;

import com.ducquy.enums.RoomType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
@Data // Sinh tự động các phương thức getter, setter, toString, equals, hashCode
@AllArgsConstructor // Sinh constructor với tất cả các trường
@NoArgsConstructor // Sinh constructor không tham số
@Builder // Hỗ trợ pattern Builder để tạo đối tượng một cách linh hoạt
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID phòng, tự động tăng

    @Min(value = 1, message = "Room number must be greater than 0") // Kiểm tra số phòng phải lớn hơn 0
    @Column(unique = true) // Đảm bảo số phòng là duy nhất trong bảng
    private Integer roomNumber; // Số phòng, duy nhất

    @Enumerated(EnumType.STRING) // Lưu trữ giá trị enum dưới dạng chuỗi
    private RoomType type; // Loại phòng (SINGLE, DOUBLE, SUITE)

    @DecimalMin(value = "0.1", message = "Price per night must be greater than 0") // Kiểm tra giá mỗi đêm phải lớn hơn 0
    private BigDecimal pricePerNight; // Giá mỗi đêm của phòng

    @Min(value = 1, message = "Capacity must be greater than 0") // Kiểm tra sức chứa phải lớn hơn 0
    private Integer capacity; // Sức chứa của phòng (số người tối đa có thể ở)

    private String description; // Mô tả về phòng

    private String imageUrl; // Đường dẫn đến hình ảnh của phòng


}
