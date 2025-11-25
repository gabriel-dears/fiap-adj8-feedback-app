package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.message.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

import java.time.LocalDateTime;

public record AlertMessageDetails(
        String studentName,
        String lessonName,
        String comment,
        String rating,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime date
) {

    public static AlertMessageDetails fromFeedback(Feedback feedback) {
        return new AlertMessageDetails(
                feedback.getStudent().getName(),
                feedback.getLesson().getName(),
                feedback.getComment(),
                feedback.getRating().toString(),
                feedback.getDate()
        );
    }

}
