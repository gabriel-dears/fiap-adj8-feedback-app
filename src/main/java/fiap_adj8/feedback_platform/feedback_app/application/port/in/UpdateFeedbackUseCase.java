package fiap_adj8.feedback_platform.feedback_app.application.port.in;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

public interface UpdateFeedbackUseCase {
    Feedback execute(Feedback feedback);
}
