package com.pioneers.PicturePublishingService.service.picture;

import com.pioneers.PicturePublishingService.config.FileStorageProperties;
import com.pioneers.PicturePublishingService.dao.PictureRepository;
import com.pioneers.PicturePublishingService.dao.UserRepository;
import com.pioneers.PicturePublishingService.error.exception.PictureNotFound;
import com.pioneers.PicturePublishingService.error.exception.UserNotFound;
import com.pioneers.PicturePublishingService.mapper.PictureMapper;
import com.pioneers.PicturePublishingService.model.dto.PictureDto;
import com.pioneers.PicturePublishingService.model.entities.Picture;
import com.pioneers.PicturePublishingService.model.entities.User;
import com.pioneers.PicturePublishingService.model.enums.Status;
import com.pioneers.PicturePublishingService.model.response.PictureResponse;
import com.pioneers.PicturePublishingService.service.fileStorage.FileStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static com.pioneers.PicturePublishingService.mapper.PictureMapper.toPicture;
import static com.pioneers.PicturePublishingService.mapper.PictureMapper.toPictureResponse;
import static com.pioneers.PicturePublishingService.utils.AuthUtil.validateUserIsLoggedIn;
import static com.pioneers.PicturePublishingService.utils.FileUtil.generateUrlFromFilePath;

@Slf4j
@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private final FileStorageProperties properties;

    @Override
    public PictureResponse uploadPicture(PictureDto pictureDto) {
        // Save file and get relative path
        String fileName = fileStorageService.saveFile(pictureDto.getFile());

        Picture picture = toPicture(pictureDto, fileName);
        User user = userRepository.findByEmail(pictureDto.getUserEmail())
                        .orElseThrow(() -> new UserNotFound("email not found with email: " + pictureDto.getUserEmail()));
        validateUserIsLoggedIn(user);

        picture.setUser(user);
        pictureRepository.save(picture);

        String fileUrl = generateUrlFromFilePath(fileName);

        return toPictureResponse(picture, fileUrl);
    }



    @Override
    public List<PictureResponse> getAllPendingPictures() {
        return pictureRepository.findByStatus(Status.PENDING).stream()
                .map(PictureMapper::mapToResponse)
                .toList();
    }

    @Override
    public List<PictureResponse> getAllAcceptedPictures() {
        return pictureRepository.findByStatus(Status.ACCEPTED).stream()
                .map(PictureMapper::mapToResponse)
                .toList();
    }

    @Override
    public void acceptPicture(UUID pictureId) {
        Picture picture = pictureRepository.findById(pictureId)
                .orElseThrow(() -> new PictureNotFound("Picture with ID " + pictureId + " not found"));
        picture.setStatus(Status.ACCEPTED);
        pictureRepository.save(picture);
        log.info("Picture accepted successfully");
    }

    @Override
    public void rejectPicture(UUID pictureId) {
        Picture picture = pictureRepository.findById(pictureId)
                .orElseThrow(() -> new PictureNotFound("Picture with ID " + pictureId + " not found"));

        try {
            Path picturePath = Path.of(properties.getUploadDir(), picture.getFilePath());
            Files.deleteIfExists(picturePath);
            log.info("Deleted file from storage: {}", picturePath);
        } catch (Exception e) {
            log.error("Failed to delete picture file: {}", picture.getFilePath(), e);
        }

        picture.setStatus(Status.REJECTED);
        pictureRepository.save(picture);
        log.info("Picture rejected successfully");
    }
}
