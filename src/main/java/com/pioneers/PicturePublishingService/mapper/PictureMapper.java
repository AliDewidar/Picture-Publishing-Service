package com.pioneers.PicturePublishingService.mapper;

import com.pioneers.PicturePublishingService.model.dto.PictureDto;
import com.pioneers.PicturePublishingService.model.entities.Picture;
import com.pioneers.PicturePublishingService.model.enums.Status;
import com.pioneers.PicturePublishingService.model.response.PictureResponse;
import com.pioneers.PicturePublishingService.utils.FileUtil;
import lombok.experimental.UtilityClass;
import java.time.LocalDateTime;


@UtilityClass
public class PictureMapper {
    public Picture toPicture(PictureDto pictureDto, String filePath) {
        return Picture.builder()
                .description(pictureDto.getDescription())
                .category(pictureDto.getCategory())
                .status(Status.PENDING)
                .filePath(filePath)
                .uploadedAt(LocalDateTime.now())
                .build();
    }

    public PictureResponse mapToResponse(Picture picture) {
        String fileUrl = FileUtil.generateUrlFromFilePath(picture.getFilePath());
        return toPictureResponse(picture, fileUrl);
    }

    public PictureResponse toPictureResponse(Picture picture, String fileUrl) {
       return PictureResponse.builder()
                .id(picture.getId())
                .description(picture.getDescription())
                .category(picture.getCategory())
                .url(fileUrl)
                .userEmail(picture.getUser().getEmail())
                .build();
    }
}
