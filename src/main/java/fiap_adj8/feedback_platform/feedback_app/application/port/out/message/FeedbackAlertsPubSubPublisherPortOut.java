package fiap_adj8.feedback_platform.feedback_app.application.port.out.message;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

public interface FeedbackAlertsPubSubPublisherPortOut {
    void publishAlert(Feedback feedback);
}