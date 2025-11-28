package fiap_adj8.feedback_platform.feedback_app.application.port.in;

import java.util.UUID;

public interface DeleteFeedbackUseCase {
    void execute(UUID feedbackId, String email);
}
