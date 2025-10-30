package fiap_adj8.feedback_platform.feedback_app.application.port.in;

import fiap_adj8.feedback_platform.feedback_app.domain.model.User;

public interface FindStudentByEmailUseCase {
    User execute(String email);
}
