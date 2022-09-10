package ekke.spring;

import ekke.spring.TestConfig.TestConfiguration;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@ContextConfiguration(
        classes = {TestConfiguration.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
@SqlGroup({
        @Sql(scripts = "/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
        @Sql(scripts = "/fill.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
})
@AutoConfigureMockMvc
public class DatabaseIntegrationTestBase {

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    protected ResultActions get(final String url){
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(url);
        return mockMvc.perform(request);
    }

    @SneakyThrows
    protected ResultActions post(final String url, final Object body){
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(url).accept(MediaType.APPLICATION_JSON);
        if (Objects.nonNull(body))
            request.contentType(MediaType.APPLICATION_JSON).content(body.toString());
        return mockMvc.perform(request);
    }

    @SneakyThrows
    protected  ResultActions put(final String url, final Object body) {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(url).accept(MediaType.APPLICATION_JSON);
        if (Objects.nonNull(body))
            request.contentType(MediaType.APPLICATION_JSON).content(body.toString());
        return mockMvc.perform(request);
    }

    @SneakyThrows
    protected ResultActions delete(final String url) {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(url);
        return mockMvc.perform(request);
    }
}
