package com.pioneers.PicturePublishingService.service.admin;

import com.pioneers.PicturePublishingService.model.dto.AdminDto;

public interface AdminService {
    void checkCredentials(AdminDto adminDto);
}
