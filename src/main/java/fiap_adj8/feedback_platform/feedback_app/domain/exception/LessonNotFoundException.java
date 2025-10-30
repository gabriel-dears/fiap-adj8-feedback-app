package fiap_adj8.feedback_platform.feedback_app.domain.exception;

public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException(String message) {
        super(message);
    }
}
