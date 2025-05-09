package com.pioneers.PicturePublishingService.model.response;

import com.pioneers.PicturePublishingService.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class PictureResponse {
    UUID id;
    String userEmail;
    String description;
    Category category;
    String url;
}
