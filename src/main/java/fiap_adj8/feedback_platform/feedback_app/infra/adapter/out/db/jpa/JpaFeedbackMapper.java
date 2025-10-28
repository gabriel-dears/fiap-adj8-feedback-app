package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import org.springframework.stereotype.Component;

@Component
public class JpaFeedbackMapper {

    public Feedback toModel(JpaFeedbackEntity jpaFeedbackEntity) {
        if (jpaFeedbackEntity == null) return null;
        Feedback feedback = new Feedback();
        feedback.setId(null);
        feedback.setLesson(null);
        feedback.setStudent(null);
        feedback.setRating(null);
        feedback.setUrgent(null);
        feedback.setComment(null);
        feedback.setDate(null);
        // TODO: populate fields
        return feedback;

    }
}
