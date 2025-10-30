package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.model.ApplicationPage;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindAllFeedbackForAdminUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;

public class FindAllFeedbackForAdminUseCaseImpl implements FindAllFeedbackForAdminUseCase {

    private final CustomFeedbackRepository customFeedbackRepository;

    public FindAllFeedbackForAdminUseCaseImpl(CustomFeedbackRepository customFeedbackRepository) {
        this.customFeedbackRepository = customFeedbackRepository;
    }

    @Override
    public ApplicationPage<Feedback> execute(Integer pageNumber, Integer pageSize) {
        return customFeedbackRepository.findAll(pageNumber, pageSize);
    }
}
