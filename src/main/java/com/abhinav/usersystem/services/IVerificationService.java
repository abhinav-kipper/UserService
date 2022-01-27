package com.abhinav.usersystem.services;

import com.abhinav.usersystem.entities.UserRequest;

public interface IVerificationService {

    Boolean verify(UserRequest userRequest);
}
