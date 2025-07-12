package com.ducquy.service;

import com.ducquy.dtos.LoginRequest;
import com.ducquy.dtos.RegistrationRequest;
import com.ducquy.dtos.Response;
import com.ducquy.dtos.UserDTO;
import com.ducquy.entitys.User;

public interface UserService {
    Response registerUser(RegistrationRequest registrationRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    Response getOwnAccountDetails();

    User getCurrentLoggedInUser();

    Response updateOwnAccount(UserDTO userDTO);

    Response deleteOwnAccount();

    Response getMyBookingHistory();
}
