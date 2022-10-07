package ekke.spring.UserControllerIt;

import ekke.spring.Application;
import ekke.spring.DatabaseIntegrationTestBase;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = Application.class)
public abstract class UserIt extends DatabaseIntegrationTestBase {
}
