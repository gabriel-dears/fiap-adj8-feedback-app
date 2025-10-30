package fiap_adj8.feedback_platform.feedback_app.application.port.in;

import fiap_adj8.feedback_platform.feedback_app.application.model.ApplicationPage;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

public interface FindAllFeedbackForStudentUseCase {
    ApplicationPage<Feedback> execute(Integer pageNumber, Integer pageSize, String email);
}
