package com.ducquy.dtos;

import com.ducquy.entitys.User;
import com.ducquy.enums.PaymentGateway;
import com.ducquy.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // Bỏ qua các thuộc tính không được định nghĩa trong DTO khi nhận dữ liệu JSON
@JsonInclude(JsonInclude.Include.NON_NULL) // Chỉ bao gồm các thuộc tính không null trong JSON
public class PaymentDTO {

    private Long id;  // ID thanh toán
    private BookingDTO booking; // Đặt phòng liên kết với giao dịch thanh toán, duy nhất để theo dõi đặt phòng

    private String transactionId; // Mã giao dịch thanh toán duy nhất từ cổng thanh toán (ví dụ: Stripe, PayPal)

    private BigDecimal amount; // Số tiền thanh toán

    private PaymentGateway paymentMethod; // Phương thức thanh toán (CREDIT_CARD, PAYPAL, BANK_TRANSFER)

    private LocalDateTime paymentDate; // Ngày thanh toán, lưu dưới dạng chuỗi (có thể chuyển đổi sang LocalDate nếu cần)

    private PaymentStatus status; // Trạng thái thanh toán (PENDING, COMPLETED, FAILED)

    private String bookingReference; // Mã tham chiếu đặt phòng liên kết với giao dịch thanh toán, duy nhất để theo dõi đặt phòng

    private String failureReason; // Lý do thất bại nếu thanh toán không thành công

    private String approvalLink; // Liên kết phê duyệt thanh toán, sử dụng cho các phương thức thanh toán yêu cầu xác nhận từ người dùng (ví dụ: PayPal, Stripe)

}
