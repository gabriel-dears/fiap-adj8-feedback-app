package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomUserRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Role;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaCustomUserRepository implements CustomUserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final JpaUserMapper jpaUserMapper;
    private final JpaUserRepositoryRunner jpaUserRepositoryRunner;

    public JpaCustomUserRepository(JpaUserRepository jpaUserRepository, JpaUserMapper jpaUserMapper, JpaUserRepositoryRunner jpaUserRepositoryRunner) {
        this.jpaUserRepository = jpaUserRepository;
        this.jpaUserMapper = jpaUserMapper;
        this.jpaUserRepositoryRunner = jpaUserRepositoryRunner;
    }

    @Override
    public Optional<User> findByNameAndRole(String email, Role role) {
        Optional<User> optResponse = Optional.empty();

        Optional<JpaUserEntity> opt = jpaUserRepositoryRunner.run(() -> jpaUserRepository.findByEmailAndRole(email, role));

        if (opt.isPresent()) {
            optResponse = Optional.of(jpaUserMapper.toModel(opt.get()));
        }
        return optResponse;
    }
}
