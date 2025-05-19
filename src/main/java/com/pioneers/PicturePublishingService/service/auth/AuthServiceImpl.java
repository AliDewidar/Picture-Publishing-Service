package com.pioneers.PicturePublishingService.service.auth;

import com.pioneers.PicturePublishingService.dao.UserRepository;
import com.pioneers.PicturePublishingService.error.exception.UserAlreadyExists;
import com.pioneers.PicturePublishingService.error.exception.UserNotFound;
import com.pioneers.PicturePublishingService.mapper.UserMapper;
import com.pioneers.PicturePublishingService.model.dto.UserDto;
import com.pioneers.PicturePublishingService.model.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.pioneers.PicturePublishingService.utils.ValidationClass.isPasswordMatched;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public void register(UserDto userDto) {
        userRepository.findByEmail(userDto.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExists("User already exists with email: " + userDto.getEmail());
                });

        User newUser = UserMapper.toUser(userDto);
        newUser.setLoggedIn(false);
        userRepository.save(newUser);

        log.debug("User registered successfully");
    }

    @Override
    public void login(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new UserNotFound("Email not found with email: " + userDto.getEmail()));

        if (!isPasswordMatched(userDto.getPassword(), user.getPassword())) {
            throw new UserNotFound("Invalid password");
        }

        user.setLoggedIn(true);
        userRepository.save(user);
        log.debug("user logged in successfully ");
    }

    @Override
    public void logout(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFound("Email not found with email: " + email));

        user.setLoggedIn(false);
        userRepository.save(user);
        log.debug("user logged out");
    }

}
