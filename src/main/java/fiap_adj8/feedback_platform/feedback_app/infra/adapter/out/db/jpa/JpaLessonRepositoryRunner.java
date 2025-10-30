package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.application.db.DbRunner;
import fiap_adj8.feedback_platform.feedback_app.application.exception.LessonRepositoryException;
import org.springframework.stereotype.Component;

@Component
public class JpaLessonRepositoryRunner extends DbRunner<LessonRepositoryException> {

    public JpaLessonRepositoryRunner() {
        super(LessonRepositoryException::new);
    }

}
