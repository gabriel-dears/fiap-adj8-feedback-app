package fiap_adj8.feedback_platform.feedback_app.application.port.out;

import fiap_adj8.feedback_platform.feedback_app.domain.model.LessonFeedbackSummary;

import java.time.LocalDate;
import java.util.List;

public interface CustomLessonFeedbackSummaryRepository {

    List<LessonFeedbackSummary> getMostRatedLessons(
            LocalDate startDate,
            LocalDate endDate
    );

    List<LessonFeedbackSummary> getHighestRatedLessons(
            LocalDate startDate,
            LocalDate endDate
    );

}
