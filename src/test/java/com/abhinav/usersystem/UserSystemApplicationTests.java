package com.abhinav.usersystem;

import com.abhinav.usersystem.algorithms.PasswordEncryptionAlgorithm;
import com.abhinav.usersystem.controllers.UserController;
import com.abhinav.usersystem.entities.UserProfile;
import com.abhinav.usersystem.entities.UserRequest;
import com.abhinav.usersystem.enums.SignUpBy;
import com.abhinav.usersystem.exceptions.ErrorMessages;
import com.abhinav.usersystem.exceptions.UserProfileServiceException;
import com.abhinav.usersystem.repositories.InMemoryRepository;
import com.abhinav.usersystem.services.FBVerificationService;
import com.abhinav.usersystem.services.IVerificationService;
import com.abhinav.usersystem.services.MobileVerificationService;
import com.abhinav.usersystem.services.UserProfileService;
import com.abhinav.usersystem.services.VerificationFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserSystemApplicationTests {

	UserController userController;

	@BeforeEach
	void setUp() {
		InMemoryRepository inMemoryRepository = new InMemoryRepository();
		IVerificationService mobileVerificationService = new MobileVerificationService();
		IVerificationService fbVerificationService = new FBVerificationService();
		VerificationFactory verificationFactory = new VerificationFactory(mobileVerificationService, fbVerificationService);
		PasswordEncryptionAlgorithm passwordEncryptionAlgorithm = new PasswordEncryptionAlgorithm();
		UserProfileService userProfileService = new UserProfileService(inMemoryRepository, verificationFactory, passwordEncryptionAlgorithm);
		userController = new UserController(userProfileService);
	}

	@Test
	void testSignupPositiveFlow() {
		UserRequest userRequest = new UserRequest("Abhinav", "Mudgal",
									"abc@gmail.com", "passwd", 9897967676L, SignUpBy.EMAIL);
		UserProfile actualResponse = userController.signup(userRequest);

		UserProfile expectedResponse = new UserProfile("0", "Abhinav", "Mudgal",
				"abc@gmail.com", "$$passwd$$", 9897967676L);
		assertEquals(expectedResponse.toString(), actualResponse.toString());
	}

	@Test
	void testSignupEmailAlreadyExistsFlow() {
		UserRequest userRequest1 = new UserRequest("Abhinav", "Mudgal",
				"abc@gmail.com", "passwd", 9897967676L, SignUpBy.EMAIL);
		userController.signup(userRequest1);

		UserRequest userRequest2 = new UserRequest("Aditya", "Mudgal",
				"abc@gmail.com", "passworddd", 9897967676L, SignUpBy.EMAIL);
		final UserProfileServiceException ex = assertThrows(UserProfileServiceException.class, () -> userController.signup(userRequest2));
		assertEquals(ErrorMessages.EMAIL_ALREADY_EXISTS_ERROR, ex.getMessage());
	}

}
