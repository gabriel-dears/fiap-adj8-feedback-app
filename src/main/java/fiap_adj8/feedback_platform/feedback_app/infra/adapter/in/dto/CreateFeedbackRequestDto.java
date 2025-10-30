package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Rating;

import java.util.UUID;

public record CreateFeedbackRequestDto(
        UUID lessonId,
        String comment,
        Rating rating,
        Boolean urgent
) {
}
