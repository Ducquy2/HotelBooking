package com.ducquy.exceptions;

import com.ducquy.dtos.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor // Tao ra constructor cho các trường được đánh dấu @Autowired
public class CustomAccessDenialHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        Response accessErrorResponse = Response.builder()
                .status(HttpStatus.FORBIDDEN.value()) // Thiết lập mã trạng thái HTTP cho response là 403 Forbidden
                .message(accessDeniedException.getMessage())
                .build();
        response.setContentType("application/json"); // Thiết lập kiểu nội dung của response là JSON
        response.setStatus(HttpStatus.FORBIDDEN.value()); // Thiết lập mã trạng thái HTTP cho response là 403 Forbidden
        response.getWriter().write(objectMapper.writeValueAsString(accessErrorResponse)); // Chuyển đổi đối tượng Response thành chuỗi JSON và ghi vào response
    }

}
