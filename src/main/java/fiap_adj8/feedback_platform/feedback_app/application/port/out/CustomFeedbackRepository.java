package fiap_adj8.feedback_platform.feedback_app.application.port.out;

import fiap_adj8.feedback_platform.feedback_app.application.model.ApplicationPage;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

import java.util.Optional;
import java.util.UUID;

public interface CustomFeedbackRepository {

    Optional<Feedback> findById(UUID id);

    Feedback create(Feedback id);

    Optional<Feedback> findByIdAndEmail(UUID id, String email);

    ApplicationPage<Feedback> findAll(Integer pageNumber, Integer pageSize);

    ApplicationPage<Feedback> findAll(Integer pageNumber, Integer pageSize, String email);
}
