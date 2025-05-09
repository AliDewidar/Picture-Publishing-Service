package com.pioneers.PicturePublishingService.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationClass {

    public static boolean isPasswordMatched(String sourcePassword, String targetPassword) {
        return targetPassword.equals(sourcePassword);
    }
}
