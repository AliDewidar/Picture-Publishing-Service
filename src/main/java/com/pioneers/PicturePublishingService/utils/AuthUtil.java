package com.pioneers.PicturePublishingService.utils;

import com.pioneers.PicturePublishingService.error.exception.UserNotLoggedIn;
import com.pioneers.PicturePublishingService.model.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthUtil {
    public static void validateUserIsLoggedIn(User user) {
        if (!user.isLoggedIn()) {
            throw new UserNotLoggedIn("you must be logged in to upload a picture");
        }
    }
}
