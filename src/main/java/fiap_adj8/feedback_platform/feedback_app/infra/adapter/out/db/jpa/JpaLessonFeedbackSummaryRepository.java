package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.domain.model.LessonFeedbackSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface JpaLessonFeedbackSummaryRepository extends JpaRepository<JpaFeedbackEntity, UUID> {

    @Query("""
                SELECT new fiap_adj8.feedback_platform.feedback_app.domain.model.LessonFeedbackSummary(
                    f.lesson.name,
                    COUNT(f),
                    AVG(CASE f.rating
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.ONE THEN 1
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.TWO THEN 2
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.THREE THEN 3
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.FOUR THEN 4
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.FIVE THEN 5
                    END)
                )
                FROM feedback f
                WHERE f.date BETWEEN :startDate AND :endDate
                GROUP BY f.lesson.name
                ORDER BY COUNT(f) DESC
            """)
    List<LessonFeedbackSummary> findMostRatedLessons(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("""
                SELECT new fiap_adj8.feedback_platform.feedback_app.domain.model.LessonFeedbackSummary(
                    f.lesson.name,
                    COUNT(f),
                    AVG(CASE f.rating
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.ONE THEN 1
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.TWO THEN 2
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.THREE THEN 3
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.FOUR THEN 4
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.FIVE THEN 5
                    END)
            )
                FROM feedback f
                WHERE f.date BETWEEN :startDate AND :endDate
                GROUP BY f.lesson.name
                HAVING COUNT(f) > 0
                ORDER BY AVG(CASE f.rating
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.ONE THEN 1
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.TWO THEN 2
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.THREE THEN 3
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.FOUR THEN 4
                        WHEN fiap_adj8.feedback_platform.feedback_app.domain.model.Rating.FIVE THEN 5
                    END) DESC
            """)
    List<LessonFeedbackSummary> findHighestRatedLessons(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );


}
