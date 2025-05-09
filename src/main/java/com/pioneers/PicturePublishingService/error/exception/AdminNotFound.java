package com.pioneers.PicturePublishingService.error.exception;

public class AdminNotFound extends BaseCustomException {

    public AdminNotFound(String description) {
        super(1003, "Admin not found", description);
    }
}
