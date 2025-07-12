package com.ducquy.service.impl;

import com.ducquy.dtos.*;
import com.ducquy.entitys.Booking;
import com.ducquy.entitys.User;
import com.ducquy.enums.UserRole;
import com.ducquy.exceptions.NotFoundException;
import com.ducquy.repository.BookingRepository;
import com.ducquy.repository.UserRepository;
import com.ducquy.security.JwtUtils;
import com.ducquy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;
    private final BookingRepository bookingRepository;


    @Override
    public Response registerUser(RegistrationRequest registrationRequest) {

        log.info("INSIDE registerUser() method of UserServiceImpl");

        UserRole role = UserRole.CUSTOMER;

        if(registrationRequest.getRole() != null){
            role = registrationRequest.getRole();
        }

        User userToSave = User.builder()
                .firstName(registrationRequest.getFirstName()) // Tên người dùng
                .lastName(registrationRequest.getLastName()) // Họ người dùng
                .email(registrationRequest.getEmail()) // Email người dùng, duy nhất
                .phoneNumber(registrationRequest.getPhoneNumber()) // Số điện thoại người dùng
                .role(role) // Vai trò người dùng (ADMIN, CUSTOMER), mặc định là CUSTOMER
                .password(passwordEncoder.encode(registrationRequest.getPassword())) // Mật khẩu người dùng, được mã hóa
                .active(true) // Mặc định tài khoản được kích hoạt
                .build();
        userRepository.save(userToSave); // Lưu người dùng vào cơ sở dữ liệu

        return Response.builder()
                .status(200)
                .message("User registered successfully")
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {

        log.info("INSIDE loginUser() method of UserServiceImpl");

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + loginRequest.getEmail())
                ); // Tìm người dùng theo email, nếu không tìm thấy thì ném ngoại lệ NotFoundException

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new NotFoundException("Invalid credentials for user: " + loginRequest.getEmail());
            // Kiểm tra mật khẩu, nếu không khớp thì ném ngoại lệ NotFoundException
        }

        String token = jwtUtils.generateToken(user.getEmail()); // Tạo token JWT cho người dùng

        return Response.builder()
                .status(200)
                .message("Login successful")
                .role(user.getRole())
                .token(token)
                .active(user.isActive())
                .expirationTime("6 months") // Thời gian hết hạn của token
                .build();
    }

    @Override
    public Response getAllUsers() {

        log.info("INSIDE getAllUsers() method of UserServiceImpl");

        // Lấy danh sách tất cả người dùng từ cơ sở dữ liệu, sắp xếp theo ID giảm dần
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        // Chuyển đổi danh sách người dùng sang danh sách UserDTO bằng ModelMapper
        List<UserDTO> userDTOList = modelMapper.map(users,new TypeToken<List<UserDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("List of all users")
                .users(userDTOList)
                .build();
    }

    @Override
    public Response getOwnAccountDetails() {

        log.info(("INSIDE getOwnAccountDetails() method of UserServiceImpl"));

        // Lấy email của người dùng hiện tại từ SecurityContext
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Tìm người dùng theo email, nếu không tìm thấy thì ném ngoại lệ NotFoundException
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return Response.builder()
                .status(200)
                .message("User account details retrieved successfully")
                .user(userDTO)
                .build();
    }

    @Override
    public User getCurrentLoggedInUser() {

        log.info("INSIDE getCurrentLoggedInUser() method of UserServiceImpl");

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Tìm người dùng theo email, nếu không tìm thấy thì ném ngoại lệ NotFoundException
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));

    }

    @Override
    public Response updateOwnAccount(UserDTO userDTO) {

        log.info("INSIDE updateOwnAccount() method of UserServiceImpl");

        User existingUser = getCurrentLoggedInUser();

        // Cập nhật thông tin người dùng nếu có thay đổi
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }

        // Kiểm tra xem người dùng có quyền cập nhật thông tin hay không
        if(userDTO.getFirstName() != null){
            existingUser.setFirstName(userDTO.getFirstName());
        }

        // Cập nhật các trường khác nếu có
        if(userDTO.getLastName() != null){
            existingUser.setLastName(userDTO.getLastName());
        }

        // Cập nhật số điện thoại nếu có
        if(userDTO.getPhoneNumber() != null){
            existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        }


        if(userDTO.getPassword() != null){
            existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        userRepository.save(existingUser);

        return Response.builder()
                .status(200)
                .message("User account updated successfully")
                .build();

    }

    @Override
    public Response deleteOwnAccount() {

        log.info("INSIDE deleteOwnAccount() method of UserServiceImpl");

        User exisingUser = getCurrentLoggedInUser();

        userRepository.delete(exisingUser);

        return Response.builder()
                .status(200)
                .message("User account deleted successfully")
                .build();
    }

    @Override
    public Response getMyBookingHistory() {

        log.info("INSIDE getMyBookingHistory() method of UserServiceImpl");

        User currentUser = getCurrentLoggedInUser();

        // Lấy danh sách đặt chỗ của người dùng hiện tại từ BookingRepository
        List<Booking> bookings = bookingRepository.findByUserId(currentUser.getId());

        // Chuyển đổi danh sách đặt chỗ sang danh sách BookingDTO bằng ModelMapper
        List<BookingDTO> bookingDTOList = modelMapper
                .map(bookings,new TypeToken<List<UserDTO>>(){}.getType());

        return Response.builder()
                .status(200)
                .message("Booking history retrieved successfully")
                .bookings(bookingDTOList)
                .build();
    }
}
