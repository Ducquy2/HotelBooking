package com.ducquy.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Cho phép CORS cho tất cả các đường dẫn
                        .allowedOrigins("*") // Cho phép tất cả các nguồn gốc
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Cho phép các phương thức HTTP
                        .allowedHeaders("*") // Cho phép tất cả các tiêu đề
                        .allowCredentials(true); // Cho phép cookie và thông tin xác thực
            }
        };
    }
}
