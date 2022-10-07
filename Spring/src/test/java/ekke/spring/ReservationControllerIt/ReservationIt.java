package ekke.spring.ReservationControllerIt;

import ekke.spring.Application;
import ekke.spring.DatabaseIntegrationTestBase;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = Application.class)
public abstract class ReservationIt extends DatabaseIntegrationTestBase {
}
