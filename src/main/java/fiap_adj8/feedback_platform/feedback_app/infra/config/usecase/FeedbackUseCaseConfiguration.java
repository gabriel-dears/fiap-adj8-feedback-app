package fiap_adj8.feedback_platform.feedback_app.infra.config.usecase;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindFeedbackByIdUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.application.service.FindFeedbackByIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeedbackUseCaseConfiguration {

    @Bean
    public FindFeedbackByIdUseCase findFeedbackByIdUseCase(CustomFeedbackRepository customFeedbackRepository) {
        return new FindFeedbackByIdUseCaseImpl(customFeedbackRepository);
    }

}
