package com.pioneers.PicturePublishingService.controller;

import com.pioneers.PicturePublishingService.model.dto.PictureDto;
import com.pioneers.PicturePublishingService.model.response.PictureResponse;
import com.pioneers.PicturePublishingService.service.picture.PictureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {

    private final PictureService pictureService;

    @PostMapping("/upload")
    public ResponseEntity<PictureResponse> uploadPictureApi(@Valid @ModelAttribute PictureDto pictureDto) throws IOException {
        PictureResponse pictureResponse = pictureService.uploadPicture(pictureDto);
        return ResponseEntity.ok(pictureResponse);
    }

    @GetMapping("/allAccepted")
    public ResponseEntity<List<PictureResponse>> getAllAcceptedPicturesApi() {
        List<PictureResponse> acceptedPictures = pictureService.getAllAcceptedPictures();
        return ResponseEntity.ok(acceptedPictures);
    }
}
