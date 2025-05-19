package com.pioneers.PicturePublishingService.utils;

import com.pioneers.PicturePublishingService.error.exception.FileSizeExceededException;
import com.pioneers.PicturePublishingService.error.exception.FileStorageException;
import com.pioneers.PicturePublishingService.error.exception.InvalidFileTypeException;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@UtilityClass
public class FileUtil {

    private final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/pjpeg", "image/png", "image/gif");
    private final Set<String> ALLOWED_EXTENSIONS = Set.of(".jpeg",".jpg", ".png", ".gif");
    String INVALID_TYPE_MSG = "Only image files with extensions jpg, png, gif are allowed";

    public void validateFile(MultipartFile file, long maxSize) {
        Objects.requireNonNull(file, "File must not be null");

        if (file.getSize() > maxSize) {
            throw new FileSizeExceededException("File size exceeds the maximum allowed size of 2MB");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new InvalidFileTypeException(INVALID_TYPE_MSG);
        }

        String originalFilename = extractOriginalFileName(file);

        String extension = extractExtension(originalFilename).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new InvalidFileTypeException(INVALID_TYPE_MSG);
        }
    }

    public String extractOriginalFileName(MultipartFile file) {
        Objects.requireNonNull(file, "File must not be null");

        String fileName = file.getOriginalFilename();
        if (fileName == null || fileName.isBlank()) {
            throw new FileStorageException("Original filename must not be null or empty");
        }
        return fileName;
    }

    public void createDirectoryIfNotExists(String uploadDir) throws IOException {
        Path dirPath = Path.of(uploadDir);
        if (Files.notExists(dirPath)) {
            Files.createDirectories(dirPath);
        }
    }

    public String extractExtension(String filename) {
        if (filename == null || filename.isBlank()) {
            throw new FileStorageException("Filename must not be null or empty");
        }

        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            throw new FileStorageException("Filename must have a valid extension");
        }

        return filename.substring(dotIndex);
    }

    public String generateUniqueFileName(String extension) {
        return UUID.randomUUID() + extension;
    }

    public Path buildPath(String uploadDir, String filename) {
        return Path.of(uploadDir, filename);
    }

    public void copyFile(MultipartFile file, Path destination, String originalFilename) {
        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Could not store the file. Please try again!", e);
        }
    }

    public String generateUrlFromFilePath(String fileName) {
        return "http://localhost:8080/uploads/" + fileName;
    }
}
