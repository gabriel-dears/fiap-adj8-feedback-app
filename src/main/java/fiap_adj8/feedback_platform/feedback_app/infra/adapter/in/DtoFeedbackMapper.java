package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.FeedbackResponseDto;

public class DtoFeedbackMapper {
    public static FeedbackResponseDto toDto(Feedback feedback) {
        return new FeedbackResponseDto(
                feedback.getId(),
                feedback.getStudent(),
                feedback.getLesson(),
                feedback.getComment(),
                feedback.getRating(),
                feedback.getUrgent(),
                feedback.getDate()
        );
    }
}
