package com.pioneers.PicturePublishingService.error.exception;

public class FileStorageException extends BaseCustomException {

    public FileStorageException(String description, Throwable cause) {
        super(1008, "File storage error", description, cause);
    }
}
