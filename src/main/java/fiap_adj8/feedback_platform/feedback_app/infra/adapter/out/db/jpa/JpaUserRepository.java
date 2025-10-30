package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUserEntity, UUID> {
    Optional<JpaUserEntity> findByEmailAndRole(String email, Role role);
}
