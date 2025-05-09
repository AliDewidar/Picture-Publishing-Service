package com.pioneers.PicturePublishingService.service.user;

import com.pioneers.PicturePublishingService.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {
    void register(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID id);
}
