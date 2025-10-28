package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Lesson;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Rating;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record FeedbackResponseDto(
        UUID id,
        User student,
        Lesson lesson,
        String comment,
        Rating rating,
        Boolean urgent,
        LocalDateTime date
) {
}
