package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.model.ApplicationPage;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindAllFeedbackForStudentUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

public class FindAllFeedbackForStudentUseCaseImpl implements FindAllFeedbackForStudentUseCase {

    private final CustomFeedbackRepository customFeedbackRepository;

    public FindAllFeedbackForStudentUseCaseImpl(CustomFeedbackRepository customFeedbackRepository) {
        this.customFeedbackRepository = customFeedbackRepository;
    }

    @Override
    public ApplicationPage<Feedback> execute(Integer pageNumber, Integer pageSize, String email) {
        return customFeedbackRepository.findAll(pageNumber, pageSize, email);
    }
}
