package ekke.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("integrationTest")
@AutoConfigureMockMvc
public abstract class IntegrationTestBase {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    private static final String emptyJson = "[]";

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

    @SneakyThrows
    protected ResultActions isOk(final ResultActions resultActions) {
        return resultActions.andExpect(status().isOk());
    }

    @SneakyThrows
    protected ResultActions isUnauthorized(final ResultActions resultActions){
        return resultActions.andExpect(status().isUnauthorized());
    }

    @SneakyThrows
    protected ResultActions hasNoAccess(final ResultActions resultActions) {
        return resultActions.andExpect(status().isForbidden());
    }

    @SneakyThrows
    protected ResultActions isNotFound(final ResultActions resultActions) {
        return resultActions.andExpect(status().isNotFound());
    }

    @SneakyThrows
    protected ResultActions isBadRequest(final ResultActions resultActions) {
        return resultActions.andExpect(status().isBadRequest());
    }

    @SneakyThrows
    protected ResultActions isConflict(final ResultActions resultActions) {
        return resultActions.andExpect(status().isConflict());
    }
}
