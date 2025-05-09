package com.pioneers.PicturePublishingService.utils;

import com.pioneers.PicturePublishingService.error.exception.BaseCustomException;
import com.pioneers.PicturePublishingService.error.model.ErrorResponse;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
@UtilityClass
public class Utils {

    public static String generateUrlFromFilePath(String fileName) {
        return "http://localhost:8080/uploads/" + fileName;
    }

    public static ResponseEntity<ErrorResponse> buildErrorResponse(BaseCustomException e, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(e.getCode(), e.getMessage(), e.getDescription());
        return new ResponseEntity<>(errorResponse, status);
    }
}
