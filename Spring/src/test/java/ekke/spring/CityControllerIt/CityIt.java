package ekke.spring.CityControllerIt;

import ekke.spring.Application;
import ekke.spring.DatabaseIntegrationTestBase;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = Application.class)
public abstract class CityIt extends DatabaseIntegrationTestBase {
}
