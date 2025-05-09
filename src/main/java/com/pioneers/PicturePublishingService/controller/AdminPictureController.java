package com.pioneers.PicturePublishingService.controller;

import com.pioneers.PicturePublishingService.model.dto.AdminDto;
import com.pioneers.PicturePublishingService.model.response.PictureResponse;
import com.pioneers.PicturePublishingService.service.admin.AdminService;
import com.pioneers.PicturePublishingService.service.picture.PictureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/picture")
public class AdminPictureController {

    private final PictureService pictureService;
    private final AdminService adminService;

    @GetMapping("/pending")
    public ResponseEntity<List<PictureResponse>> getAllPendingPicturesApi(@Valid @RequestBody AdminDto adminDto) {

        adminService.checkCredentials(adminDto);

        List<PictureResponse> pendingPictures = pictureService.getAllPendingPictures();
        return ResponseEntity.ok(pendingPictures);
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<String> acceptPictureApi(@Valid @PathVariable UUID id, @RequestBody AdminDto adminDto) {

        adminService.checkCredentials(adminDto);

        pictureService.acceptPicture(id);
        return ResponseEntity.ok("Picture accepted successfully");
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<String> rejectPictureApi(@Valid @PathVariable UUID id, @RequestBody AdminDto adminDto) {

        adminService.checkCredentials(adminDto);

        pictureService.rejectPicture(id);
        return ResponseEntity.ok("Picture rejected successfully");
    }
}

