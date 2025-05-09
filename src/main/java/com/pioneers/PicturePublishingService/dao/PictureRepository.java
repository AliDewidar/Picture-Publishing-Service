package com.pioneers.PicturePublishingService.dao;

import com.pioneers.PicturePublishingService.model.entities.Picture;
import com.pioneers.PicturePublishingService.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PictureRepository extends JpaRepository<Picture, UUID> {
    List<Picture> findByStatus(Status status);
}
