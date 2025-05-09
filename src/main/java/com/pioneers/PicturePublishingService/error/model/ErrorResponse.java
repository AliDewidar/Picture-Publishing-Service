package com.pioneers.PicturePublishingService.error.model;

public record ErrorResponse(
         int code,
         String message,
         String description
) {}
