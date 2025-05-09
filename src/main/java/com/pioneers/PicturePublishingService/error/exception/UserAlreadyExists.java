package com.pioneers.PicturePublishingService.error.exception;

public class UserAlreadyExists extends BaseCustomException {

    public UserAlreadyExists(String description) {
        super(1000, "User Already Exist", description);
    }
}
