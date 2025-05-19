package com.pioneers.PicturePublishingService.service.fileStorage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileStorageService {
    String saveFile(MultipartFile file) throws IOException;
}
