package fiap_adj8.feedback_platform.feedback_app.application.port.in;

import fiap_adj8.feedback_platform.feedback_app.domain.model.LessonFeedbackSummary;

import java.time.LocalDate;
import java.util.List;

public interface FindMostRatedFeedbackUseCase {
    List<LessonFeedbackSummary> execute(LocalDate startDate, LocalDate endDate);
}
