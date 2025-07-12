package com.ducquy.security;

import com.ducquy.entitys.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class AuthUser implements UserDetails {

    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> user.getRole().name());
        // Trả về danh sách quyền của người dùng, ở đây chỉ có một quyền là role của người dùng
    }

    @Override
    public String getPassword() {
        return user.getPassword();
        // Trả về mật khẩu của người dùng
    }

    @Override
    public String getUsername() {
        return user.getEmail();
        // Trả về email của người dùng làm tên đăng nhập
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        // Trả về true nếu tài khoản không bị khóa
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // Trả về true nếu tài khoản không hết hạn
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
        // Trả về true nếu tài khoản đang hoạt động
    }
}