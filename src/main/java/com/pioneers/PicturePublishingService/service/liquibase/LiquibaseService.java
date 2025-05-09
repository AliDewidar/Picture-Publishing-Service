package com.pioneers.PicturePublishingService.service.liquibase;

import liquibase.exception.LiquibaseException;

import java.sql.SQLException;

public interface LiquibaseService {
    void rollback(int count) throws SQLException, LiquibaseException;
}
