package com.ducquy.dtos;

import com.ducquy.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    //generic response class for API responses
    private int status; // HTTP status code
    private String message; // Message to be returned in the response

    //for login response
    private String token; // JWT token for authentication
    private UserRole role; // Role of the user (ADMIN, CUSTOMER)
    private boolean active;
    private String expirationTime; // Expiration time of the token in ISO 8601 format

    //user data
    private UserDTO user;
    private List<UserDTO> users;

    //booking data
    private BookingDTO booking;
    private List<BookingDTO> bookings;

    //room data
    private RoomDTO room;
    private List<RoomDTO> rooms;


    //payment data
    private String transactionId;
    private PaymentDTO payment;
    private List<PaymentDTO> payments;

    // room Notification data
    private NotificationDTO notification;
    private List<NotificationDTO> notifications;

    private final LocalDateTime timestamp = LocalDateTime.now(); // Timestamp of the response, default to the current time









}
