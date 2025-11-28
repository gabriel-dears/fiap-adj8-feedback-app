package fiap_adj8.feedback_platform.feedback_app.infra.config.usecase;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.*;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.db.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.db.CustomLessonFeedbackSummaryRepository;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.db.CustomLessonRepository;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.db.CustomUserRepository;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.message.FeedbackAlertsPubSubPublisherPortOut;
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
    public CreateFeedbackUseCase createFeedbackUseCase(CustomFeedbackRepository customFeedbackRepository, FindStudentByEmailUseCase findStudentByEmailUseCase, FindLessonByIdUseCase findLessonByIdUseCase, FeedbackAlertsPubSubPublisherPortOut feedbackAlertsPubSubPublisherPortOut) {
        return new CreateFeedbackByIdUseCaseImpl(customFeedbackRepository, findStudentByEmailUseCase, findLessonByIdUseCase, feedbackAlertsPubSubPublisherPortOut);
    }

    @Bean
    FindFeedbackByIdForStudentUseCase findFeedbackByIdForStudentUseCase(CustomFeedbackRepository customFeedbackRepository) {
        return new FindFeedbackByIdForStudentUseCaseImpl(customFeedbackRepository);
    }

    @Bean
    FindAllFeedbackForAdminUseCase findAllFeedbackForAdminUseCase(CustomFeedbackRepository customFeedbackRepository) {
        return new FindAllFeedbackForAdminUseCaseImpl(customFeedbackRepository);
    }

    @Bean
    FindAllFeedbackForStudentUseCase findAllFeedbackForStudentUseCase(CustomFeedbackRepository customFeedbackRepository) {
        return new FindAllFeedbackForStudentUseCaseImpl(customFeedbackRepository);
    }

    @Bean
    FindMostRatedFeedbackUseCase findMostRatedFeedbackUseCase(CustomLessonFeedbackSummaryRepository customLessonFeedbackSummaryRepository) {
        return new FindMostRatedFeedbackUseCaseImpl(customLessonFeedbackSummaryRepository);
    }

    @Bean
    FindHighestRankedFeedbackUseCase findHighestRankedFeedbackUseCase(CustomLessonFeedbackSummaryRepository customLessonFeedbackSummaryRepository) {
        return new FindHighestRankedFeedbackUseCaseImpl(customLessonFeedbackSummaryRepository);
    }

    @Bean
    UpdateFeedbackUseCase updateFeedbackUseCase(CustomFeedbackRepository customFeedbackRepository, FeedbackAlertsPubSubPublisherPortOut feedbackAlertsPubSubPublisherPortOut, FindStudentByEmailUseCase findStudentByEmailUseCase) {
        return new UpdateFeedbackUseCaseImpl(customFeedbackRepository, feedbackAlertsPubSubPublisherPortOut, findStudentByEmailUseCase);
    }

    @Bean
    DeleteFeedbackUseCase deleteFeedbackUseCase(CustomFeedbackRepository customFeedbackRepository) {
        return new DeleteFeedbackUseCaseImpl(customFeedbackRepository);
    }
}
