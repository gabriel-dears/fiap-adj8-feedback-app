package fiap_adj8.feedback_platform.feedback_app.application.port.in;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

import java.util.UUID;

public interface FindFeedbackByIdUseCase {
    Feedback execute(UUID id);
}
