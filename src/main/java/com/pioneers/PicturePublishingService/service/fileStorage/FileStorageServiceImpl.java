package com.pioneers.PicturePublishingService.service.fileStorage;

import com.pioneers.PicturePublishingService.config.FileStorageProperties;
import com.pioneers.PicturePublishingService.error.exception.FileSizeExceededException;
import com.pioneers.PicturePublishingService.error.exception.FileStorageException;
import com.pioneers.PicturePublishingService.error.exception.InvalidFileTypeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Set;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final FileStorageProperties properties;
    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png", "image/gif");


    @Override
    public String saveFile(MultipartFile file) {

        if (file.getSize() > properties.getMaxSize()) {
            throw new FileSizeExceededException("File size exceeds the maximum allowed size of 2MB");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new InvalidFileTypeException("Only JPG, PNG, and GIF files are allowed");
        }

        File dir = new File(properties.getUploadDir());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }

        String newFileName = UUID.randomUUID() + extension;
        Path destination = Path.of(properties.getUploadDir(), newFileName); //concat uploads + new file  ex: uploads/4588dd85d5d85Isis5d8d.png

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Failed to save file: {}", originalFilename, e);
            throw new FileStorageException("Could not store the file. Please try again!", e);
        }

        return newFileName;
    }
}
