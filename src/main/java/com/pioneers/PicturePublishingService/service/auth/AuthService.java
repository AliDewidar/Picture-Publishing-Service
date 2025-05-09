package com.pioneers.PicturePublishingService.service.auth;

import com.pioneers.PicturePublishingService.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    void register(UserDto userDto);
    void login(UserDto userDto);
    void logout(String email);
}
