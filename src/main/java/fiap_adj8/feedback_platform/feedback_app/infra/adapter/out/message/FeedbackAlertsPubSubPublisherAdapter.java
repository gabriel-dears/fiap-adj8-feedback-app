package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.message;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.message.FeedbackAlertsPubSubPublisherPortOut;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.message.dto.AlertMessageDetails;
import org.springframework.stereotype.Component;

@Component
public class FeedbackAlertsPubSubPublisherAdapter implements FeedbackAlertsPubSubPublisherPortOut {

    private final PubSubTemplate pubSubTemplate;

    public FeedbackAlertsPubSubPublisherAdapter(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }

    @Override
    public void publishAlert(Feedback feedback) {
        AlertMessageDetails message = AlertMessageDetails.fromFeedback(feedback);
        try {
            System.out.println(message);

            pubSubTemplate.publish("feedback-alerts", message)
                    .thenAccept(messageId ->
                            System.out.println("✅ Message sent to PubSub with ID: " + messageId)
                    )
                    .exceptionally(ex -> {
                        System.err.println("❌ Failed to publish message: " + ex.getMessage());
                        return null;
                    });

        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize PubSub message", e);
        }
    }
}
