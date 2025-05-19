package com.pioneers.PicturePublishingService.mapper;

import com.pioneers.PicturePublishingService.model.dto.UserDto;
import com.pioneers.PicturePublishingService.model.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public User toUser(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
