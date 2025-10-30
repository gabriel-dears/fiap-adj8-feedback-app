package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in;

import com.fasterxml.jackson.databind.ObjectMapper;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Rating;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.CreateFeedbackRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class FeedbackRestControllerIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    private static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("feedback_test_db")
                    .withUsername("user")
                    .withPassword("pass");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    private static final String STUDENT_EMAIL = "student@email.com";
    private static final String STUDENT_PASSWORD = "student";

    @Test
    void shouldReturn404WhenFeedbackNotFound() throws Exception {
        mockMvc.perform(get("/feedback/" + UUID.randomUUID())
                        .with(httpBasic(STUDENT_EMAIL, STUDENT_PASSWORD)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateFeedbackAndRetrieveSuccessfully() throws Exception {
        String comment = "random comment";
        CreateFeedbackRequestDto request = new CreateFeedbackRequestDto(
                UUID.fromString("e8d1f5c4-7122-4d1b-8e5c-0a9b1b9e3a99"),
                comment,
                Rating.FIVE,
                true
        );

        String postResponse = mockMvc.perform(post("/feedback")
                        .with(httpBasic(STUDENT_EMAIL, STUDENT_PASSWORD))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.comment").value(comment))
                .andReturn()
                .getResponse()
                .getContentAsString();

        var createdFeedback = objectMapper.readTree(postResponse);
        String id = createdFeedback.get("id").asText();

        mockMvc.perform(get("/feedback/" + id)
                        .with(httpBasic(STUDENT_EMAIL, STUDENT_PASSWORD)))
                .andExpect(status().isOk());
    }
}
