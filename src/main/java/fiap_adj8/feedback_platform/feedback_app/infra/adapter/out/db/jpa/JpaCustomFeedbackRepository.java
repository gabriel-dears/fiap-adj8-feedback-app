package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.application.model.ApplicationPage;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCustomFeedbackRepository implements CustomFeedbackRepository {

    private final JpaFeedbackRepository jpaFeedbackRepository;
    private final JpaFeedbackMapper jpaFeedbackMapper;
    private final JpaFeedbackRepositoryRunner jpaFeedbackRepositoryRunner;

    public JpaCustomFeedbackRepository(JpaFeedbackRepository jpaFeedbackRepository, JpaFeedbackMapper jpaFeedbackMapper, JpaFeedbackRepositoryRunner jpaFeedbackRepositoryRunner) {
        this.jpaFeedbackRepository = jpaFeedbackRepository;
        this.jpaFeedbackMapper = jpaFeedbackMapper;
        this.jpaFeedbackRepositoryRunner = jpaFeedbackRepositoryRunner;
    }

    @Override
    public Optional<Feedback> findById(UUID id) {
        Optional<JpaFeedbackEntity> opt = jpaFeedbackRepositoryRunner.run(() -> jpaFeedbackRepository.findById(id));
        if (opt.isEmpty()) return Optional.empty();
        Feedback feedback = jpaFeedbackMapper.toModel(opt.get());
        return Optional.of(feedback);
    }

    @Override
    public Feedback create(Feedback feedback) {
        JpaFeedbackEntity inputEntity = jpaFeedbackMapper.toEntity(feedback);
        JpaFeedbackEntity entity = jpaFeedbackRepositoryRunner.run(() -> jpaFeedbackRepository.save(inputEntity));
        return jpaFeedbackMapper.toModel(entity);
    }

    @Override
    public Optional<Feedback> findByIdAndEmail(UUID id, String email) {
        Optional<JpaFeedbackEntity> opt = jpaFeedbackRepositoryRunner.run(() -> jpaFeedbackRepository.findByIdAndStudent_Email(id, email));
        if (opt.isEmpty()) return Optional.empty();
        Feedback feedback = jpaFeedbackMapper.toModel(opt.get());
        return Optional.of(feedback);
    }

    @Override
    public ApplicationPage<Feedback> findAll(Integer pageNumber, Integer pageSize) {
        Page<JpaFeedbackEntity> jpaPage = jpaFeedbackRepositoryRunner.run(() -> jpaFeedbackRepository.findAll(PageRequest.of(pageNumber, pageSize)));
        return jpaFeedbackMapper.toApplicationPage(jpaPage);
    }

    @Override
    public ApplicationPage<Feedback> findAll(Integer pageNumber, Integer pageSize, String email) {
        Page<JpaFeedbackEntity> jpaPage = jpaFeedbackRepositoryRunner.run(() -> jpaFeedbackRepository.findByStudent_Email(email, PageRequest.of(pageNumber, pageSize)));
        return jpaFeedbackMapper.toApplicationPage(jpaPage);
    }

    @Override
    public boolean existsByLessonAndStudent(UUID lessonId, UUID studentId) {
        return jpaFeedbackRepositoryRunner.run(() -> jpaFeedbackRepository.existsByStudent_IdAndLesson_Id(studentId, lessonId));
    }

}
