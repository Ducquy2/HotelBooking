package com.ducquy.enums;

public enum PaymentStatus {
    PENDING, // Thanh toán đang chờ xử lý
    COMPLETED, // Thanh toán đã hoàn thành
    FAILED, // Thanh toán thất bại
    REFUNDED, // Thanh toán đã được hoàn tiền
    REVERSED // Thanh toán đã bị đảo ngược (ví dụ: chargeback)
}
