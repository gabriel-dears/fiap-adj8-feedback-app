package fiap_adj8.feedback_platform.feedback_app.application.port.out;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Role;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface CustomUserRepository {

    Optional<User> findByIdAndRole(UUID id, Role role);

}
