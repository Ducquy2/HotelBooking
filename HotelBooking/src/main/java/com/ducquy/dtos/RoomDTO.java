package com.ducquy.dtos;

import com.ducquy.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true) // Bỏ qua các thuộc tính không được định nghĩa trong DTO khi nhận dữ liệu JSON
@JsonInclude(JsonInclude.Include.NON_NULL) // Chỉ bao gồm các thuộc tính không null trong JSON
public class RoomDTO {

    private Long id; // ID phòng

    private Integer roomNumber; // Số phòng, duy nhất

    private RoomType type; // Loại phòng (SINGLE, DOUBLE, SUITE)

    private BigDecimal pricePerNight; // Giá mỗi đêm của phòng

    private Integer capacity; // Sức chứa của phòng (số người tối đa có thể ở)

    private String description; // Mô tả về phòng

    private String imageUrl; // Đường dẫn đến hình ảnh của phòng


}
