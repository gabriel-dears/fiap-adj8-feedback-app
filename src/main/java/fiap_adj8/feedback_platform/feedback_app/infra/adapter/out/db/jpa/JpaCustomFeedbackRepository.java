package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCustomFeedbackRepository implements CustomFeedbackRepository {

    private final JpaFeedbackRepository jpaFeedbackRepository;
    private final JpaFeedbackMapper jpaFeedbackMapper;

    public JpaCustomFeedbackRepository(JpaFeedbackRepository jpaFeedbackRepository, JpaFeedbackMapper jpaFeedbackMapper) {
        this.jpaFeedbackRepository = jpaFeedbackRepository;
        this.jpaFeedbackMapper = jpaFeedbackMapper;
    }

    @Override
    public Optional<Feedback> findById(UUID id) {
        Optional<JpaFeedbackEntity> opt = jpaFeedbackRepository.findById(id);
        if (opt.isEmpty()) return Optional.empty();
        Feedback feedback = jpaFeedbackMapper.toModel(opt.get());
        return Optional.of(feedback);
    }
}
