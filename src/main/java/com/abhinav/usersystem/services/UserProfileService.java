package com.abhinav.usersystem.services;

import static com.abhinav.usersystem.exceptions.ErrorMessages.EMAIL_ALREADY_EXISTS_ERROR;
import static com.abhinav.usersystem.exceptions.ErrorMessages.EMAIL_REQUIRED_ERROR;
import static com.abhinav.usersystem.exceptions.ErrorMessages.PHONE_ALREADY_EXISTS_ERROR;
import static com.abhinav.usersystem.exceptions.ErrorMessages.PHONE_NUMBER_REQUIRED_ERROR;
import static com.abhinav.usersystem.exceptions.ErrorMessages.USER_PROFILE_NOT_FOUND;

import java.util.Objects;

import com.abhinav.usersystem.algorithms.PasswordEncryptionAlgorithm;
import com.abhinav.usersystem.entities.UserRequest;
import com.abhinav.usersystem.entities.UserProfile;

import com.abhinav.usersystem.enums.Verification;
import com.abhinav.usersystem.exceptions.UserProfileServiceException;
import com.abhinav.usersystem.repositories.IRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService implements IUserProfileService {

    private final IRepository<UserProfile> userProfileRepository;
    private final VerificationFactory verificationFactory;
    private final PasswordEncryptionAlgorithm passwordEncryptionAlgorithm;

    public UserProfileService(@Qualifier("inMemoryRepository") IRepository<UserProfile> userProfileRepository, VerificationFactory verificationFactory, PasswordEncryptionAlgorithm passwordEncryptionAlgorithm) {
        this.userProfileRepository = userProfileRepository;
        this.verificationFactory = verificationFactory;
        this.passwordEncryptionAlgorithm = passwordEncryptionAlgorithm;
    }

    @Override
    public UserProfile createUserProfile(UserRequest userRequest) {
        if (Objects.isNull(userRequest.getSignUpBy())) {
            throw new RuntimeException("signUpBy field must not be empty.");
        }

        UserProfile userProfile = null;
        switch (userRequest.getSignUpBy()) {
            case EMAIL:
                if (Objects.isNull(userRequest.getEmailAddress())) {
                    throw new UserProfileServiceException(EMAIL_REQUIRED_ERROR);
                }
                if (userProfileRepository.findEmail(userRequest.getEmailAddress())) {
                    throw new UserProfileServiceException(EMAIL_ALREADY_EXISTS_ERROR);
                }
                userProfile = new UserProfile(userRequest);
                passwordEncryptionAlgorithm.encryptPassword(userProfile);
                userProfileRepository.save(userProfile);
                break;
            case OTP:
                if (Objects.isNull(userRequest.getPhoneNumber())) {
                    throw new UserProfileServiceException(PHONE_NUMBER_REQUIRED_ERROR);
                }
                if (userProfileRepository.findPhone(userRequest.getPhoneNumber())) {
                    throw new UserProfileServiceException(PHONE_ALREADY_EXISTS_ERROR);
                }

                IVerificationService verificationService = verificationFactory.getVerificationService(Verification.OTP);
                if (verificationService.verify(userRequest)) {
                    userProfile = new UserProfile(userRequest);
                    userProfileRepository.save(userProfile);
                }
                break;

            case SOCIAL:
                break;
            default:
        }

        return userProfile;
    }

    @Override
    public UserProfile getUserProfile(String userId) {

        UserProfile userProfile = userProfileRepository.retrieve(userId);

        if (Objects.isNull(userProfile)) {
            throw new UserProfileServiceException(USER_PROFILE_NOT_FOUND);
        }
        return userProfile;
    }

    @Override
    public UserProfile editUserProfile(String userId, UserRequest userRequest) {
        UserProfile existingProfile = getUserProfile(userId);
        UserProfile userProfile = new UserProfile(userRequest, existingProfile);
        passwordEncryptionAlgorithm.encryptPassword(userProfile);
        return userProfileRepository.update(userProfile);
    }

    @Override
    public void deleteUser(String userId) {
        UserProfile userProfile = getUserProfile(userId);
        userProfileRepository.delete(userId);
    }
}
