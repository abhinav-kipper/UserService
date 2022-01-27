package com.abhinav.usersystem.services;

public interface IUserProfileService {

    public OperationStatus createUserProfile(UserDetails userDetails);
    public UserProfile getUserProfile(UserDetails userDetails); // can getUserByEmail or getUserByUserId or getuserbyphone
    public UserProfile editUserProfile(UserDetails userDetails);
    public OperationStatus deleteUser(UserDetails userDetails);
}
