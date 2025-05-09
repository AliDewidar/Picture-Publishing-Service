package com.pioneers.PicturePublishingService.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDto {

    @NotBlank(message = "Username is required and cannot be empty")
    private String username;

    @NotBlank(message = "Password is required and cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
