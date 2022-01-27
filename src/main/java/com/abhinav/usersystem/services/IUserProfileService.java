package com.abhinav.usersystem.services;

import com.abhinav.usersystem.entities.UserRequest;
import com.abhinav.usersystem.entities.UserProfile;


public interface IUserProfileService {

    UserProfile createUserProfile(UserRequest userRequest);
    UserProfile getUserProfile(String userId);
    UserProfile editUserProfile(String userId, UserRequest userRequest);
    void deleteUser(String userId);
}
