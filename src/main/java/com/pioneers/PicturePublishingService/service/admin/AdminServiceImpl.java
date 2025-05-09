package com.pioneers.PicturePublishingService.service.admin;

import com.pioneers.PicturePublishingService.dao.AdminRepository;
import com.pioneers.PicturePublishingService.error.exception.AdminNotFound;
import com.pioneers.PicturePublishingService.model.dto.AdminDto;
import com.pioneers.PicturePublishingService.model.entities.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.pioneers.PicturePublishingService.utils.ValidationClass.isPasswordMatched;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public void checkCredentials(AdminDto adminDto) {
       Admin admin = adminRepository.findByUsername(adminDto.getUsername())
                .orElseThrow(() -> new AdminNotFound("Invalid username"));

       if (!isPasswordMatched(adminDto.getPassword(), admin.getPassword())) {
           throw new AdminNotFound("Invalid password");
       }
    }
}
