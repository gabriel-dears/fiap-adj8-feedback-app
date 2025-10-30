package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.application.db.DbRunner;
import fiap_adj8.feedback_platform.feedback_app.application.exception.FeedbackRepositoryException;
import org.springframework.stereotype.Component;

@Component
public class JpaFeedbackRepositoryRunner extends DbRunner<FeedbackRepositoryException> {

    public JpaFeedbackRepositoryRunner() {
        super(FeedbackRepositoryException::new);
    }

}
