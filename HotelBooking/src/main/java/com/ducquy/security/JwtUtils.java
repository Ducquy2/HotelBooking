package com.ducquy.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
public class JwtUtils {

    // Thời gian hết hạn của token, ở đây là 6 tháng
    private static final long EXPIRATION_TIME_IN_MILSEC = 100L * 60L * 60L * 24L * 30L * 6L;
    private SecretKey key;

    // Lấy chuỗi bí mật từ file cấu hình
    @Value("${secreteJwtString}")
    private String secreteJwtString;

    // Khởi tạo khóa bí mật từ chuỗi bí mật
    @PostConstruct
    private void init() {
        byte[] keyByte = secreteJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyByte, "HmacSHA256");
    }

    // tạo token từ email
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MILSEC))
                .signWith(key)
                .compact();
    }

    // kiem tra xem token có hợp lệ hay không
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
        // Lấy tên người dùng từ token
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
        // Phân tích token và lấy các claim
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token); // Lấy tên người dùng từ token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        // Kiểm tra xem tên người dùng trong token có khớp với tên người dùng trong UserDetails và token có hết hạn hay không
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
        // Kiểm tra xem token có hết hạn hay không
    }








}
