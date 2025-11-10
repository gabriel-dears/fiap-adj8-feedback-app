package fiap_adj8.feedback_platform.feedback_app.application.port.out;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Role;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {
    Optional<User> findByNameAndRole(String email, Role role);

    List<String> findAllAdminEmails();
}
