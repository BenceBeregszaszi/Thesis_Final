package ekke.spring;

import ekke.spring.TestConfig.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(
        classes = {TestConfiguration.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
public class DatabaseIntegrationTestBase {
}
