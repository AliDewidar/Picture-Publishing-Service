package com.pioneers.PicturePublishingService.error.exception;

public class InvalidFileTypeException extends BaseCustomException {

    public InvalidFileTypeException(String description) {
        super(1007, "Invalid file type", description);
    }
}
