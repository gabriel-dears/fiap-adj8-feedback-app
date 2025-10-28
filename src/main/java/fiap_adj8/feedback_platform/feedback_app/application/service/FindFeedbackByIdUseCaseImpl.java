package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindFeedbackByIdUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.FeedbackNotFoundException;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

import java.util.UUID;

public class FindFeedbackByIdUseCaseImpl implements FindFeedbackByIdUseCase {

    private final CustomFeedbackRepository customFeedbackRepository;

    public FindFeedbackByIdUseCaseImpl(CustomFeedbackRepository customFeedbackRepository) {
        this.customFeedbackRepository = customFeedbackRepository;
    }

    @Override
    public Feedback execute(UUID id) {
        return customFeedbackRepository.findById(id).orElseThrow(() -> new FeedbackNotFoundException("Feedback not found"));
    }
}
