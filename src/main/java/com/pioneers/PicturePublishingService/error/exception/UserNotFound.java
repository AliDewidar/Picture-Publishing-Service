package com.pioneers.PicturePublishingService.error.exception;

public class UserNotFound extends BaseCustomException {

    public UserNotFound(String description) {
        super(1001, "User not found", description);
    }
}
