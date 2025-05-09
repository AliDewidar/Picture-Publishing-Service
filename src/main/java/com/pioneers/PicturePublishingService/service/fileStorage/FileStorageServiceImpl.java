package com.pioneers.PicturePublishingService.service.fileStorage;

import com.pioneers.PicturePublishingService.config.FileStorageProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.util.Objects;

import static com.pioneers.PicturePublishingService.utils.FileUtil.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final FileStorageProperties properties;

    @Override
    public String saveFile(MultipartFile file) {

        validateFileSize(file, properties.getMaxSize());

        validateFileType(file);

        createDirectoryIfNotExists(properties.getUploadDir());

        String originalFilename = Objects.requireNonNull(file.getOriginalFilename());

        String extension = extractExtension(originalFilename);

        String newFileName = generateUniqueFileName(extension);

        Path destination = buildPath(properties.getUploadDir(), newFileName);

        copyFile(file, destination, originalFilename);

        return newFileName;
    }
}
