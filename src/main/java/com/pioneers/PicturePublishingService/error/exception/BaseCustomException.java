package com.pioneers.PicturePublishingService.error.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public abstract class BaseCustomException extends RuntimeException {

    int code;
    String message;
    String description;

    public BaseCustomException(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public BaseCustomException(int code, String message, String description, Throwable cause) {
        super(message + ": " + description, cause);
        this.code = code;
        this.message = message;
        this.description = description;
    }
}
