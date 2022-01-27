package com.abhinav.usersystem.services;

import com.abhinav.usersystem.enums.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class VerificationFactory {

    private final IVerificationService mobileVerificationService;

    private final IVerificationService facebookVerificationService;

    public VerificationFactory(@Qualifier("mobileVerificationService") IVerificationService mobileVerificationService, @Qualifier("FBVerificationService") IVerificationService facebookVerificationService) {
        this.mobileVerificationService = mobileVerificationService;
        this.facebookVerificationService = facebookVerificationService;
    }

    public IVerificationService getVerificationService(Verification verification) {
        switch (verification) {
            case OTP:
                return mobileVerificationService;
            case FACEBOOK:
                return facebookVerificationService;
        }
        return null;
    }

}
