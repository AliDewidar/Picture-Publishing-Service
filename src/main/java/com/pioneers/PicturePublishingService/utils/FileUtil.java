package com.pioneers.PicturePublishingService.utils;

import com.pioneers.PicturePublishingService.error.exception.FileSizeExceededException;
import com.pioneers.PicturePublishingService.error.exception.FileStorageException;
import com.pioneers.PicturePublishingService.error.exception.InvalidFileTypeException;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@UtilityClass
public class FileUtil {

    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png", "image/gif");

    public static void validateFileSize(MultipartFile file, long maxSize) {
        Objects.requireNonNull(file, "File must not be null");

        if (file.getSize() > maxSize) {
            throw new FileSizeExceededException("File size exceeds the maximum allowed size of 2MB");
        }
    }

    public static void validateFileType(MultipartFile file) {
        Objects.requireNonNull(file, "File must not be null");

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new InvalidFileTypeException("Only JPG, PNG, and GIF files are allowed");
        }
    }

    public static void createDirectoryIfNotExists(String uploadDir) {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static String extractExtension(String filename) {
        Objects.requireNonNull(filename, "Filename must not be null");
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex != -1) ? filename.substring(dotIndex) : "";
    }

    public static String generateUniqueFileName(String extension) {
        return UUID.randomUUID() + extension;
    }

    public static Path buildPath(String uploadDir, String filename) {
        return Path.of(uploadDir, filename);
    }

    public static void copyFile(MultipartFile file, Path destination, String originalFilename) {
        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileStorageException("Could not store the file. Please try again!", e);
        }
    }

    public static String generateUrlFromFilePath(String fileName) {
        return "http://localhost:8080/uploads/" + fileName;
    }
}
