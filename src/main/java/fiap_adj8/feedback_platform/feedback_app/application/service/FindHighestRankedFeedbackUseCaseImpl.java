package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindHighestRankedFeedbackUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomLessonFeedbackSummaryRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.LessonFeedbackSummary;

import java.time.LocalDate;
import java.util.List;

public class FindHighestRankedFeedbackUseCaseImpl implements FindHighestRankedFeedbackUseCase {
    private final CustomLessonFeedbackSummaryRepository customLessonFeedbackSummaryRepository;

    public FindHighestRankedFeedbackUseCaseImpl(CustomLessonFeedbackSummaryRepository customLessonFeedbackSummaryRepository) {
        this.customLessonFeedbackSummaryRepository = customLessonFeedbackSummaryRepository;
    }

    @Override
    public List<LessonFeedbackSummary> execute(LocalDate startDate, LocalDate endDate) {
        return customLessonFeedbackSummaryRepository.getHighestRatedLessons(startDate, endDate);
    }
}
