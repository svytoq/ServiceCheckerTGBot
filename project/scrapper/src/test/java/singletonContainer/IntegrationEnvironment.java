package singletonContainer;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IntegrationEnvironment {

    protected static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER;
    private static final Path MIGRATIONS_PATH = new File("migrations").toPath().toAbsolutePath();

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:15")
                .withDatabaseName("scrapper")
                .withUsername("postgres")
                .withPassword("postgres");
        POSTGRE_SQL_CONTAINER.start();


        try (Connection connection = DriverManager.getConnection(POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                    POSTGRE_SQL_CONTAINER.getUsername(), POSTGRE_SQL_CONTAINER.getPassword())){

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new liquibase.Liquibase("master.xml", new DirectoryResourceAccessor(MIGRATIONS_PATH), database);
            liquibase.update(new Contexts(), new LabelExpression());

        } catch (SQLException | FileNotFoundException | LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }
}
