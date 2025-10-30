package fiap_adj8.feedback_platform.feedback_app.application.exception;

public class StudentHasAlreadyCreatedFeedbackForThatLesson extends RuntimeException {
    public StudentHasAlreadyCreatedFeedbackForThatLesson(String message) {
        super(message);
    }
}
