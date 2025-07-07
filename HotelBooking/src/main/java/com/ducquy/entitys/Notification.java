package com.ducquy.entitys;

import com.ducquy.enums.NotificationType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications") // Tên bảng trong cơ sở dữ liệu
@Data // Sinh tự động các phương thức getter, setter, toString, equals, hashCode
@AllArgsConstructor // Sinh constructor với tất cả các trường
@NoArgsConstructor // Sinh constructor không tham số
@Builder // Hỗ trợ pattern Builder để tạo đối tượng một cách linh hoạt
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID thông báo, tự động tăng

    private String subject; // Tiêu đề thông báo
    @NotBlank(message = "Recipient is required")
    private String recipient; // Người nhận thông báo, có thể là email hoặc số điện thoại
    private String body; // Nội dung thông báo

    @Enumerated(EnumType.STRING) // Lưu trữ giá trị enum dưới dạng chuỗi
    private NotificationType type; // Loại thông báo (EMAIL, SMS, PUSH)

    private String bookingReference; // Mã tham chiếu đặt phòng liên kết với thông báo, duy nhất để theo dõi đặt phòng
    private final LocalDateTime createdAt = LocalDateTime.now(); // Ngày tạo thông báo, mặc định là ngày hiện tại

}
