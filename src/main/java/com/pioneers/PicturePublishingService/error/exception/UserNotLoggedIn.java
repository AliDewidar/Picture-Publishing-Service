package com.pioneers.PicturePublishingService.error.exception;

public class UserNotLoggedIn extends BaseCustomException {

    public UserNotLoggedIn(String description) {
        super(1004, "User not logged in", description);
    }
}
