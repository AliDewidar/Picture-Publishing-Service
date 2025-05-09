package com.pioneers.PicturePublishingService.model.dto;

import com.pioneers.PicturePublishingService.model.enums.Category;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class PictureDto {

    @NotBlank(message = "userEmail is required")
    @Email(message = "please enter a valid email")
    private String userEmail;

    @NotBlank(message = "description is required and cannot be empty")
    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    @NotNull(message = "category is required")
    private Category category;

    @NotNull(message = "file is required")
    private MultipartFile file;
}
