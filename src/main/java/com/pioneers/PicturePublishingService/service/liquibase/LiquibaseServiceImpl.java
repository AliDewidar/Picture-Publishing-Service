package com.pioneers.PicturePublishingService.service.liquibase;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@RequiredArgsConstructor
@Service
public class LiquibaseServiceImpl implements LiquibaseService {

    private static final String CLASS_NAME = LiquibaseServiceImpl.class.getSimpleName();

    private final DataSource dataSource;

    public void rollback(int rollbackCount) throws SQLException, LiquibaseException {
        final String methodName = "rollback/" + CLASS_NAME;

        final Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(new JdbcConnection(dataSource.getConnection()));

        try {
            final Liquibase liquibase = new Liquibase("db/changelog/db.changelog-master.xml",
                    new ClassLoaderResourceAccessor(), database);
            liquibase.rollback(rollbackCount, String.valueOf(new Contexts()));
        } catch (Exception e) {
            log.error("{}, Error during rollback!!, message: {} ", methodName, e.getMessage());
            throw new LiquibaseException("Error during rollback!!", e);
        }
    }
}
