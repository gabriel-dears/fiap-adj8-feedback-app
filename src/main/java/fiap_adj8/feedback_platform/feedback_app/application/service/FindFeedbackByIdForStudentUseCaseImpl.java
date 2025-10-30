package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindFeedbackByIdForStudentUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.FeedbackNotFoundException;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

import java.util.UUID;

public class FindFeedbackByIdForStudentUseCaseImpl implements FindFeedbackByIdForStudentUseCase {

    private final CustomFeedbackRepository customFeedbackRepository;

    public FindFeedbackByIdForStudentUseCaseImpl(CustomFeedbackRepository customFeedbackRepository) {
        this.customFeedbackRepository = customFeedbackRepository;
    }

    @Override
    public Feedback execute(UUID id, String email) {
        return customFeedbackRepository.findByIdAndEmail(id, email).orElseThrow(() -> new FeedbackNotFoundException("Feedback not found"));
    }
}
