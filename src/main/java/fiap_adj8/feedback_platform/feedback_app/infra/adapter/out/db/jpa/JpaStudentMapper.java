package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class JpaStudentMapper {

    public User toModel(JpaUserEntity jpaUserEntity) {
        User user = new User();
        user.setId(jpaUserEntity.getId());
        user.setName(jpaUserEntity.getName());
        user.setEmail(jpaUserEntity.getEmail());
        user.setRole(jpaUserEntity.getRole());
        return user;
    }
}
