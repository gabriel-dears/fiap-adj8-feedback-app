package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import org.springframework.stereotype.Component;

@Component
public class JpaFeedbackMapper {

    private final JpaLessonMapper jpaLessonMapper;
    private final JpaStudentMapper jpaStudentMapper;

    public JpaFeedbackMapper(JpaLessonMapper jpaLessonMapper, JpaStudentMapper jpaStudentMapper) {
        this.jpaLessonMapper = jpaLessonMapper;
        this.jpaStudentMapper = jpaStudentMapper;
    }

    public Feedback toModel(JpaFeedbackEntity jpaFeedbackEntity) {
        if (jpaFeedbackEntity == null) return null;
        Feedback feedback = new Feedback();
        feedback.setId(jpaFeedbackEntity.getId());
        feedback.setLesson(jpaLessonMapper.toModel(jpaFeedbackEntity.getLesson()));
        feedback.setStudent(jpaStudentMapper.toModel(jpaFeedbackEntity.getStudent()));
        feedback.setRating(jpaFeedbackEntity.getRating());
        feedback.setUrgent(jpaFeedbackEntity.getUrgent());
        feedback.setComment(jpaFeedbackEntity.getComment());
        feedback.setDate(jpaFeedbackEntity.getDate());
        return feedback;

    }

    public JpaFeedbackEntity toEntity(Feedback feedback) {
        JpaFeedbackEntity jpaFeedbackEntity = new JpaFeedbackEntity();
        jpaFeedbackEntity.setId(feedback.getId());
        jpaFeedbackEntity.setRating(feedback.getRating());
        jpaFeedbackEntity.setUrgent(feedback.getUrgent());
        jpaFeedbackEntity.setComment(feedback.getComment());
        jpaFeedbackEntity.setDate(feedback.getDate());
        jpaFeedbackEntity.setLesson(jpaLessonMapper.toEntity(feedback.getLesson()));
        jpaFeedbackEntity.setStudent(jpaStudentMapper.toEntity(feedback.getStudent()));
        return jpaFeedbackEntity;
    }
}
