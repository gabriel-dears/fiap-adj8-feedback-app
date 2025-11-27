package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.application.port.out.db.CustomLessonFeedbackSummaryRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.LessonFeedbackSummary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public class JpaCustomLessonFeedbackSummaryRepository implements CustomLessonFeedbackSummaryRepository {

    public static final Pageable TOP_TEN = PageRequest.of(0, 10);
    private final JpaLessonFeedbackSummaryRepository jpaLessonFeedbackSummaryRepository;

    public JpaCustomLessonFeedbackSummaryRepository(JpaLessonFeedbackSummaryRepository jpaLessonFeedbackSummaryRepository) {
        this.jpaLessonFeedbackSummaryRepository = jpaLessonFeedbackSummaryRepository;
    }

    @Override
    public List<LessonFeedbackSummary> getMostRatedLessons(LocalDate startDate, LocalDate endDate) {
        return jpaLessonFeedbackSummaryRepository.findMostRatedLessons(getStartTime(startDate), getEndTime(endDate), TOP_TEN);
    }

    @Override
    public List<LessonFeedbackSummary> getHighestRatedLessons(LocalDate startDate, LocalDate endDate) {
        return jpaLessonFeedbackSummaryRepository.findHighestRatedLessons(getStartTime(startDate), getEndTime(endDate), TOP_TEN);
    }

    private LocalDateTime getStartTime(LocalDate startDate) {
        return startDate.atTime(LocalTime.MIN);
    }

    private LocalDateTime getEndTime(LocalDate endDate) {
        return endDate.atTime(LocalTime.MAX);
    }

}
