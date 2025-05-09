package com.pioneers.PicturePublishingService.service.picture;

import com.pioneers.PicturePublishingService.model.dto.PictureDto;
import com.pioneers.PicturePublishingService.model.response.PictureResponse;

import java.util.List;
import java.util.UUID;

public interface PictureService {

    PictureResponse uploadPicture(PictureDto request);

    List<PictureResponse> getAllPendingPictures();

    List<PictureResponse> getAllAcceptedPictures();

    void acceptPicture(UUID pictureId);

    void rejectPicture(UUID pictureId);
}
