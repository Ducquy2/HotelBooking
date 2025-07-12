package com.ducquy.dtos;

import com.ducquy.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // Bỏ qua các thuộc tính không được định nghĩa trong DTO khi nhận dữ liệu JSON
@JsonInclude(JsonInclude.Include.NON_NULL) // Chỉ bao gồm các thuộc tính không null trong JSON
public class NotificationDTO {

    private Long id; // ID thông báo

    @NotBlank(message = "Subject is required")
    private String subject; // Tiêu đề thông báo

    @NotBlank(message = "Recipient is required")
    private String recipient; // Người nhận thông báo, có thể là email hoặc số điện thoại
    private String body; // Nội dung thông báo

    private NotificationType type; // Loại thông báo (EMAIL, SMS, PUSH)

    private String bookingReference; // Mã tham chiếu đặt phòng liên kết với thông báo, duy nhất để theo dõi đặt phòng
    private LocalDateTime createdAt; // Ngày tạo thông báo, mặc định là ngày hiện tại


}
