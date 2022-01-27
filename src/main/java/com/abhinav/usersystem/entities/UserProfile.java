package com.abhinav.usersystem.entities;

import java.util.Objects;


import com.abhinav.usersystem.utils.CommonUtils;

public class UserProfile {

    private String userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private Long phoneNumber;

    public UserProfile(UserRequest userRequest) {
        this.userId = CommonUtils.createID(); // UUID.randomUUID().toString();
        this.emailAddress = userRequest.getEmailAddress();
        this.firstName = userRequest.getFirstName();
        this.lastName = userRequest.getLastName();
        this.password = userRequest.getPassword();
        this.phoneNumber = userRequest.getPhoneNumber();
    }

    public UserProfile(UserRequest userRequest, UserProfile oldProfile) {
        this.userId = Objects.requireNonNullElse(oldProfile.getUserId(), CommonUtils.createID());
        this.emailAddress = Objects.requireNonNullElse(userRequest.getEmailAddress(), oldProfile.getEmailAddress());
        this.firstName = Objects.requireNonNullElse(userRequest.getFirstName(), oldProfile.getFirstName());
        this.lastName = Objects.requireNonNullElse(userRequest.getLastName(), oldProfile.getLastName());
        this.password = Objects.requireNonNullElse(userRequest.getPassword(), oldProfile.getPassword());
        this.phoneNumber = Objects.requireNonNullElse(userRequest.getPhoneNumber(), oldProfile.getPhoneNumber());
    }

    public UserProfile(String userId, String firstName, String lastName, String emailAddress, String password, Long phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
