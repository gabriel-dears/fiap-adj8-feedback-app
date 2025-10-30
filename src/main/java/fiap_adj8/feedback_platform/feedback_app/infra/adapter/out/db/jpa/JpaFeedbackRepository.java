package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaFeedbackRepository extends JpaRepository<JpaFeedbackEntity, UUID> {
    Optional<JpaFeedbackEntity> findByIdAndStudent_Email(UUID id, String studentEmail);

    Page<JpaFeedbackEntity> findByStudent_Email(String studentEmail, Pageable pageable);

    boolean existsByStudent_IdAndLesson_Id(UUID studentId, UUID lessonId);
}
