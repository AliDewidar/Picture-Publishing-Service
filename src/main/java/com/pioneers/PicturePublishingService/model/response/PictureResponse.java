package com.pioneers.PicturePublishingService.model.response;

import com.pioneers.PicturePublishingService.model.enums.Category;
import lombok.Builder;
import java.util.UUID;

@Builder
public record PictureResponse(UUID id, String userEmail, String description, Category category, String url) {
}
