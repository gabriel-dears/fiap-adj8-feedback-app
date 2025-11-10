package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomLessonFeedbackSummaryRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.LessonFeedbackSummary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class JpaCustomLessonFeedbackSummaryRepository implements CustomLessonFeedbackSummaryRepository {

    private final JpaLessonFeedbackSummaryRepository jpaLessonFeedbackSummaryRepository;

    public JpaCustomLessonFeedbackSummaryRepository(JpaLessonFeedbackSummaryRepository jpaLessonFeedbackSummaryRepository) {
        this.jpaLessonFeedbackSummaryRepository = jpaLessonFeedbackSummaryRepository;
    }

    @Override
    public List<LessonFeedbackSummary> getMostRatedLessons(LocalDate startDate, LocalDate endDate) {
        return jpaLessonFeedbackSummaryRepository.findMostRatedLessons(startDate, endDate);
    }

    @Override
    public List<LessonFeedbackSummary> getHighestRatedLessons(LocalDate startDate, LocalDate endDate) {
        return jpaLessonFeedbackSummaryRepository.findHighestRatedLessons(startDate, endDate);
    }
}
