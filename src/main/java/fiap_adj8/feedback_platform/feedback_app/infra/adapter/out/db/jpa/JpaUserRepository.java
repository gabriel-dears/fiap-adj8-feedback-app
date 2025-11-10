package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<JpaUserEntity, UUID> {
    Optional<JpaUserEntity> findByEmailAndRole(String email, Role role);

    @Query("""
                select u.email from app_user u
                where u.role = 'ADMIN'
            """)
    List<String> findAllAdminEmails();
}
