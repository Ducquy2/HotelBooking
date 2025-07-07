package com.ducquy.entitys;


import com.ducquy.enums.PaymentGateway;
import com.ducquy.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments") // Tên bảng trong cơ sở dữ liệu
@Data // Sinh tự động các phương thức getter, setter, toString, equals, hashCode
@AllArgsConstructor // Sinh constructor với tất cả các trường
@NoArgsConstructor // Sinh constructor không tham số
@Builder // Hỗ trợ pattern Builder để tạo đối tượng một cách linh hoạt
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // ID thanh toán, tự động tăng

    private String transactionId; // Mã giao dịch thanh toán, duy nhất để theo dõi giao dịch
    private BigDecimal amount; // Số tiền thanh toán
    private PaymentGateway paymentGateway; // Cổng thanh toán được sử dụng (VNPAY, MOMO, ZALOPAY, PAYPAL, STRIPE)

    private LocalDateTime paymentDate; // Ngày thanh toán, lưu dưới dạng chuỗi (có thể chuyển đổi sang LocalDate nếu cần)
    private PaymentStatus paymentStatus; // Trạng thái thanh toán (PENDING, COMPLETED, FAILED)
    private String bookingReference; // Mã tham chiếu đặt phòng liên kết với giao dịch thanh toán, duy nhất để theo dõi đặt phòng
    private String failureReason; // Lý do thất bại nếu thanh toán không thành công

    @ManyToOne // Nhiều thanh toán có thể liên kết với một người dùng
    @JoinColumn(name = "user_id") // Liên kết với bảng users thông qua user_id
    private User user; // Người dùng thực hiện thanh toán, liên kết với bảng users

}
