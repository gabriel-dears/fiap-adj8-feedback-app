package fiap_adj8.feedback_platform.feedback_app.infra.config.usecase;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.CreateFeedbackUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindFeedbackByIdUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindLessonByIdUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindStudentByIdUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomLessonRepository;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomUserRepository;
import fiap_adj8.feedback_platform.feedback_app.application.service.CreateFeedbackByIdUseCaseImpl;
import fiap_adj8.feedback_platform.feedback_app.application.service.FindFeedbackByIdUseCaseImpl;
import fiap_adj8.feedback_platform.feedback_app.application.service.FindLessonByIdUseCaseImpl;
import fiap_adj8.feedback_platform.feedback_app.application.service.FindStudentByIdUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeedbackUseCaseConfiguration {

    @Bean
    public FindFeedbackByIdUseCase findFeedbackByIdUseCase(CustomFeedbackRepository customFeedbackRepository) {
        return new FindFeedbackByIdUseCaseImpl(customFeedbackRepository);
    }

    @Bean
    public FindLessonByIdUseCase findLessonByIdUseCase(CustomLessonRepository customLessonRepository) {
        return new FindLessonByIdUseCaseImpl(customLessonRepository);
    }

    @Bean
    public FindStudentByIdUseCase findStudentByIdUseCase(CustomUserRepository customUserRepository) {
        return new FindStudentByIdUseCaseImpl(customUserRepository);
    }

    @Bean
    public CreateFeedbackUseCase createFeedbackUseCase(CustomFeedbackRepository customFeedbackRepository, FindStudentByIdUseCase findStudentByIdUseCase, FindLessonByIdUseCase findLessonByIdUseCase) {
        return new CreateFeedbackByIdUseCaseImpl(customFeedbackRepository, findStudentByIdUseCase, findLessonByIdUseCase);
    }

}
