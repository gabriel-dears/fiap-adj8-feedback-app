package fiap_adj8.feedback_platform.feedback_app.application.exception;

public class OnlyStudentsCanCreateFeedbackException extends RuntimeException {
    public OnlyStudentsCanCreateFeedbackException(String message) {
        super(message);
    }
}
