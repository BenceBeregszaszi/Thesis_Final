package ekke.spring;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DatabaseIntegrationTestBase extends IntegrationTestBase {
    private static final String H2_BACKUP_SQL = String.format("/tmp/test-backup%s.sql", UUID.randomUUID());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    protected void backupDb() {
        jdbcTemplate.execute(String.format("SCRIPT TO '%s'", H2_BACKUP_SQL));
    }

    @AfterEach
    protected void resetDb() {
        jdbcTemplate.execute("DROP ALL OBJECTS");
        jdbcTemplate.execute(String.format("RUNSCRIPT FROM '%S'", H2_BACKUP_SQL));
    }

    @AfterAll
    protected static void afterAll() throws IOException {
        FileUtils.forceDelete(new File(H2_BACKUP_SQL));
    }
}
