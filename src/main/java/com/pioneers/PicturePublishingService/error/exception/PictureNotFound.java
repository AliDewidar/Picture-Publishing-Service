package com.pioneers.PicturePublishingService.error.exception;

public class PictureNotFound extends BaseCustomException {

    public PictureNotFound(String description) {
        super(1002, "Picture not found", description);
    }
}
