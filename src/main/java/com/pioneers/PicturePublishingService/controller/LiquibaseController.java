package com.pioneers.PicturePublishingService.controller;

import com.pioneers.PicturePublishingService.service.liquibase.LiquibaseService;
import liquibase.exception.LiquibaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/liquibase")
public class LiquibaseController {

    private final LiquibaseService liquibaseService;

    @PutMapping("/rollback/{count}")
    public void rollbackApi(@PathVariable int count) throws SQLException, LiquibaseException {
        liquibaseService.rollback(count);
    }
}
