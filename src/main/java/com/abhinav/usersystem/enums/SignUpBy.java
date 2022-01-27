package com.abhinav.usersystem.enums;

public enum SignUpBy {
    OTP("otp"),
    EMAIL("email"),
    SOCIAL("social");

    public String key;
    SignUpBy(String signup) {
        this.key = signup;
    }

}
