package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindStudentByEmailUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.UpdateFeedbackUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.db.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.message.FeedbackAlertsPubSubPublisherPortOut;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.FeedbackNotFoundException;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;

public class UpdateFeedbackUseCaseImpl implements UpdateFeedbackUseCase {

    private final CustomFeedbackRepository customFeedbackRepository;
    private final FeedbackAlertsPubSubPublisherPortOut feedbackAlertsPubSubPublisherPortOut;
    private final FindStudentByEmailUseCase findStudentByEmailUseCase;

    public UpdateFeedbackUseCaseImpl(CustomFeedbackRepository customFeedbackRepository, FeedbackAlertsPubSubPublisherPortOut feedbackAlertsPubSubPublisherPortOut, FindStudentByEmailUseCase findStudentByEmailUseCase) {
        this.customFeedbackRepository = customFeedbackRepository;
        this.feedbackAlertsPubSubPublisherPortOut = feedbackAlertsPubSubPublisherPortOut;
        this.findStudentByEmailUseCase = findStudentByEmailUseCase;
    }

    @Override
    public Feedback execute(Feedback feedback) {

        // TODO: regra de só poder atualizar... o mesmo studentId que criou e validar para admin nao conseguir
        // TODO: colocar todos os endpoints novos e regras no readme (incluive a regra de somente um feedback por aula p msm student)

        Feedback existingFeedback = customFeedbackRepository.findById(feedback.getId()).orElseThrow(() -> new FeedbackNotFoundException(String.format("Feedback with id %s not found", feedback.getId())));

        if(!feedback.getStudent().getEmail().equals(existingFeedback.getStudent().getEmail())) {
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

    private User getStudent(Feedback feedback) {
        String email = feedback.getStudent().getEmail();
        return findStudentByEmailUseCase.execute(email);
    }

} 
