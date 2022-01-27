package com.abhinav.usersystem.controllers;



import javax.validation.Valid;

import com.abhinav.usersystem.entities.UserProfile;


import com.abhinav.usersystem.entities.UserRequest;

import com.abhinav.usersystem.services.IUserProfileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

private final IUserProfileService userProfileService;

    public UserController(IUserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserProfile signup(@RequestBody UserRequest userSignupRequest) {
        System.out.println("Received user sign up request:" + userSignupRequest.toString());
        return userProfileService.createUserProfile(userSignupRequest);
    }

    @GetMapping(value = "/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserProfile getProfile(@PathVariable("id") String userId) {
        System.out.println("Received request to get profile for:" + userId);
        return userProfileService.getUserProfile(userId);
    }

    @DeleteMapping(value = "/profile/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteProfile(@PathVariable("id") String userId) {
        System.out.println("Received request to delete profile for:" + userId);
        userProfileService.deleteUser(userId);
        return ResponseEntity.ok("Profile successfully deleted.");
    }

    @PutMapping(value = "/profile/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserProfile editProfile(@RequestBody UserRequest userEditRequest, @PathVariable String id) {
        System.out.println("Received user edit profile request:" + userEditRequest.toString());
        return userProfileService.editUserProfile(id, userEditRequest);
    }
}

/** Additions - Creating handler class and building internal POJO using builder pattern **/