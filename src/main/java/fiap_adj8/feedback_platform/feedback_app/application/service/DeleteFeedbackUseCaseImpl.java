package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.DeleteFeedbackUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.db.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.FeedbackNotFoundException;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

import java.util.UUID;

public class DeleteFeedbackUseCaseImpl implements DeleteFeedbackUseCase {

    private final CustomFeedbackRepository customFeedbackRepository;

    public DeleteFeedbackUseCaseImpl(CustomFeedbackRepository customFeedbackRepository) {
        this.customFeedbackRepository = customFeedbackRepository;
    }

    @Override
    public void execute(UUID feedbackId, String email) {
        Feedback feedback = customFeedbackRepository.findById(feedbackId).orElseThrow(() -> new FeedbackNotFoundException(String.format("Feedback with id %s not found", feedbackId)));

        if( !email.equals(feedback.getStudent().getEmail()) ) {
            throw new RuntimeException("Only the student who created the feedback can delete it.");
        }

        customFeedbackRepository.delete(feedbackId);
    }
}
