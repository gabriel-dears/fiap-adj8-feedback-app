package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.UpdateFeedbackUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.db.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.message.FeedbackAlertsPubSubPublisherPortOut;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.FeedbackNotFoundException;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

public class UpdateFeedbackUseCaseImpl implements UpdateFeedbackUseCase {

    private final CustomFeedbackRepository customFeedbackRepository;
    private final FeedbackAlertsPubSubPublisherPortOut feedbackAlertsPubSubPublisherPortOut;

    public UpdateFeedbackUseCaseImpl(CustomFeedbackRepository customFeedbackRepository, FeedbackAlertsPubSubPublisherPortOut feedbackAlertsPubSubPublisherPortOut) {
        this.customFeedbackRepository = customFeedbackRepository;
        this.feedbackAlertsPubSubPublisherPortOut = feedbackAlertsPubSubPublisherPortOut;
    }

    @Override
    public Feedback execute(Feedback feedback) {
        Feedback existingFeedback = customFeedbackRepository.findById(feedback.getId()).orElseThrow(() -> new FeedbackNotFoundException(String.format("Feedback with id %s not found", feedback.getId())));

        if (!feedback.getStudent().getEmail().equals(existingFeedback.getStudent().getEmail())) {
            throw new RuntimeException("Only the student who created the feedback can update it.");
        }

        boolean wasUrgent = existingFeedback.getUrgent();

        existingFeedback.setComment(feedback.getComment());
        existingFeedback.setRating(feedback.getRating());
        existingFeedback.setUrgent(feedback.getUrgent());

        Feedback updatedFeedback = customFeedbackRepository.update(existingFeedback);

        if (!wasUrgent && updatedFeedback.getUrgent()) {
            feedbackAlertsPubSubPublisherPortOut.publishAlert(updatedFeedback);
        }

        return updatedFeedback;
    }

} 
