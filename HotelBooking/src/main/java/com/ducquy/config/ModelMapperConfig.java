package com.ducquy.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cấu hình ModelMapper để mapping giữa các đối tượng
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Tạo và cấu hình ModelMapper bean
     * ModelMapper giúp tự động chuyển đổi dữ liệu giữa các object cùng cấu trúc
     * Ví dụ: Entity -> DTO hoặc DTO -> Entity
     *
     * @return ModelMapper đã được cấu hình
     */
    @Bean
    public ModelMapper modelMapper() {
        // Khởi tạo ModelMapper instance
        ModelMapper modelMapper = new ModelMapper();
        
        // Cấu hình ModelMapper
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper;
    }
}