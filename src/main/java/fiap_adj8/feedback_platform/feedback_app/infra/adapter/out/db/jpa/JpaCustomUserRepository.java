package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomUserRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Role;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCustomUserRepository implements CustomUserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserMapper jpaUserMapper;

    public JpaCustomUserRepository(JpaUserRepository jpaUserRepository, JpaUserMapper jpaUserMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaUserMapper = jpaUserMapper;
    }

    // TODO: wrap all db operations and create custom exceptions

    @Override
    public Optional<User> findByIdAndRole(UUID id, Role role) {
        Optional<User> optResponse = Optional.empty();
        Optional<JpaUserEntity> opt = jpaUserRepository.findByIdAndRole(id, role);
        if (opt.isPresent()) {
            optResponse = Optional.of(jpaUserMapper.toModel(opt.get()));
        }
        return optResponse;
    }
}
