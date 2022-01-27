package com.abhinav.usersystem.algorithms;

import com.abhinav.usersystem.entities.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptionAlgorithm {

    public UserProfile encryptPassword(UserProfile userProfile) {
        userProfile.setPassword(encrypt(userProfile.getPassword()));
        return userProfile;
    }
    public String encrypt(String password) {
        return "$$" + password +"$$";
    }

    public String decrypt(String password) {
        return password.substring(2, password.length()-3) ;
    }
}
