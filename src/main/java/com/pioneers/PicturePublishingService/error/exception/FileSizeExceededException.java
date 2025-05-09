package com.pioneers.PicturePublishingService.error.exception;

public class FileSizeExceededException extends BaseCustomException {

    public FileSizeExceededException(String description) {
        super(1006, "File size exceeded", description);
    }
}
