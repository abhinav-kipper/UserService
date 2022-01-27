package com.abhinav.usersystem.entities;



import com.abhinav.usersystem.enums.SignUpBy;
import com.fasterxml.jackson.annotation.JsonProperty;


public class UserRequest {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private Long phoneNumber;

    @JsonProperty("signupBy")
    private SignUpBy signUpBy;

    public UserRequest() {
    }

    public UserRequest(String firstName, String lastName, String emailAddress, String password, Long phoneNumber, SignUpBy signUpBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.signUpBy = signUpBy;
    }

    public SignUpBy getSignUpBy() {
        return signUpBy;
    }

    public void setSignUpBy(SignUpBy signUpBy) {
        this.signUpBy = signUpBy;
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
        return "UserDetails{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
