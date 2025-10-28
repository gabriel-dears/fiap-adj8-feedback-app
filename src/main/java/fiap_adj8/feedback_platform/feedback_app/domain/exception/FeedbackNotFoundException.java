package fiap_adj8.feedback_platform.feedback_app.domain.exception;

public class FeedbackNotFoundException extends RuntimeException {
    public FeedbackNotFoundException(String message) {
        super(message);
    }
}
