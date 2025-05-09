package com.pioneers.PicturePublishingService.error;

import com.pioneers.PicturePublishingService.error.exception.*;
import com.pioneers.PicturePublishingService.error.model.ErrorResponse;
import jdk.dynalink.beans.StaticClass;
import liquibase.exception.LiquibaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.pioneers.PicturePublishingService.utils.Utils.buildErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidationException(MethodArgumentNotValidException e) {
        List<ErrorResponse> errorResponseList = new ArrayList<>();

        e.getBindingResult().getAllErrors().forEach(error -> {
            ErrorResponse errorResponse = new ErrorResponse(1000, ((FieldError) error).getField(), error.getDefaultMessage());
            errorResponseList.add(errorResponse);
        });

        return ResponseEntity.badRequest().body(errorResponseList);
    }

    @ExceptionHandler(UserAlreadyExists.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExists e) {
        return buildErrorResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFound e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PictureNotFound.class)
    public ResponseEntity<ErrorResponse> handlePictureNotFound(PictureNotFound e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AdminNotFound.class)
    public ResponseEntity<ErrorResponse> handleAdminNotFound(AdminNotFound e) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotLoggedIn.class)
    public ResponseEntity<ErrorResponse> handleUserNotLoggedIn(UserNotLoggedIn e) {
        return buildErrorResponse(e, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(LiquibaseException.class)
    public ResponseEntity<ErrorResponse> handleLiquibaseException(LiquibaseException e) {
        ErrorResponse error = new ErrorResponse(1005, "Liquibase error", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(FileSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleFileSizeExceededException(FileSizeExceededException e) {
        return buildErrorResponse(e, HttpStatus.PAYLOAD_TOO_LARGE);
    }

    @ExceptionHandler(InvalidFileTypeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFileTypeException(InvalidFileTypeException e) {
        return buildErrorResponse(e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponse> handleFileStorageException(FileStorageException e) {
        return buildErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
