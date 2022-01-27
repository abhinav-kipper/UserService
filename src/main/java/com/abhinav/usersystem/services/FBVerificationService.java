package com.abhinav.usersystem.services;

import com.abhinav.usersystem.entities.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class FBVerificationService implements IVerificationService{
    @Override
    public Boolean verify(UserRequest userRequest) {
        return true;
    }
}
