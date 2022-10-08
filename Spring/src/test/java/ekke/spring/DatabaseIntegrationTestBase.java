package ekke.spring;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SqlGroup({
        @Sql(value = "classpath:reset.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "classpath:fill.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
})
public abstract class DatabaseIntegrationTestBase extends IntegrationTestBase {
}
