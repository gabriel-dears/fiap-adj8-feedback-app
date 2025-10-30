package fiap_adj8.feedback_platform.feedback_app.infra.config.usecase;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.*;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomLessonRepository;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomUserRepository;
import fiap_adj8.feedback_platform.feedback_app.application.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeedbackUseCaseConfiguration {

    @Bean
    public FindFeedbackByIdForAdminUseCase findFeedbackByIdUseCase(CustomFeedbackRepository customFeedbackRepository) {
        return new FindFeedbackByIdForAdminUseCaseImpl(customFeedbackRepository);
    }

    @Bean
    public FindLessonByIdUseCase findLessonByIdUseCase(CustomLessonRepository customLessonRepository) {
        return new FindLessonByIdUseCaseImpl(customLessonRepository);
    }

    @Bean
    public FindStudentByEmailUseCase findStudentByIdUseCase(CustomUserRepository customUserRepository) {
        return new FindStudentByEmailUseCaseImpl(customUserRepository);
    }

    @Bean
    public CreateFeedbackUseCase createFeedbackUseCase(CustomFeedbackRepository customFeedbackRepository, FindStudentByEmailUseCase findStudentByEmailUseCase, FindLessonByIdUseCase findLessonByIdUseCase) {
        return new CreateFeedbackByIdUseCaseImpl(customFeedbackRepository, findStudentByEmailUseCase, findLessonByIdUseCase);
    }

    @Bean
    FindFeedbackByIdForStudentUseCase findFeedbackByIdForStudentUseCase(CustomFeedbackRepository customFeedbackRepository) {
        return new FindFeedbackByIdForStudentUseCaseImpl(customFeedbackRepository);
    }


}
