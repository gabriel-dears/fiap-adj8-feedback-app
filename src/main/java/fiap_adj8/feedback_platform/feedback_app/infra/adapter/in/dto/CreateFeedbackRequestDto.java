package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CreateFeedbackRequestDto(
        @NotNull
        UUID lessonId,
        String comment,
        @NotNull
        @Pattern(regexp = "FIVE|FOUR|THREE|TWO|ONE", message = "Rating value must be ONE, TWO, THREE, FOUR or FIVE")
        String rating,
        Boolean urgent
) {
}
