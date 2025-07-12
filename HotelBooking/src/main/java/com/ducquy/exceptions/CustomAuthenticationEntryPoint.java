package com.ducquy.exceptions;

import com.ducquy.dtos.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor // Tao ra constructor cho các trường được đánh dấu @Autowired
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        Response authErrorResponse = Response.builder()
                .status(HttpStatus.UNAUTHORIZED.value()) // Thiết lập mã trạng thái HTTP cho response là 401 Unauthorized
                .message(authException.getMessage())
                .build();

        response.setContentType("application/json"); // Thiết lập kiểu nội dung của response là JSON
        response.setStatus(HttpStatus.UNAUTHORIZED.value()); // Thiết lập mã trạng thái HTTP cho response là 401 Unauthorized
        response.getWriter().write(objectMapper.writeValueAsString(authErrorResponse)); // Chuyển đổi đối tượng Response thành chuỗi JSON và ghi vào response
    }
}
