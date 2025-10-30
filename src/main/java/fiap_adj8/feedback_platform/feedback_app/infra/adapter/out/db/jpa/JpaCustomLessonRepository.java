package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomLessonRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Lesson;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCustomLessonRepository implements CustomLessonRepository {

    private final JpaLessonRepository jpaLessonRepository;
    private final JpaLessonMapper jpaLessonMapper;
    private final JpaLessonRepositoryRunner jpaLessonRepositoryRunner;

    public JpaCustomLessonRepository(JpaLessonRepository jpaLessonRepository, JpaLessonMapper jpaLessonMapper, JpaLessonRepositoryRunner jpaLessonRepositoryRunner) {
        this.jpaLessonRepository = jpaLessonRepository;
        this.jpaLessonMapper = jpaLessonMapper;
        this.jpaLessonRepositoryRunner = jpaLessonRepositoryRunner;
    }

    @Override
    public Optional<Lesson> findById(UUID id) {
        Optional<Lesson> optionalResponse = Optional.empty();
        Optional<JpaLessonEntity> optionalJpaLessonEntity = jpaLessonRepositoryRunner.run(() -> jpaLessonRepository.findById(id));
        if (optionalJpaLessonEntity.isPresent()) {
            optionalResponse = Optional.of(jpaLessonMapper.toModel(optionalJpaLessonEntity.get()));
        }
        return optionalResponse;
    }
}
