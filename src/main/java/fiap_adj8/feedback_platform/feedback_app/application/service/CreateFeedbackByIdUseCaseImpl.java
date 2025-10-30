package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.CreateFeedbackUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindLessonByIdUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindStudentByEmailUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Lesson;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;

import java.util.UUID;

public class CreateFeedbackByIdUseCaseImpl implements CreateFeedbackUseCase {

    private final CustomFeedbackRepository customFeedbackRepository;
    private final FindStudentByEmailUseCase findStudentByEmailUseCase;
    private final FindLessonByIdUseCase findLessonByIdUseCase;

    public CreateFeedbackByIdUseCaseImpl(CustomFeedbackRepository customFeedbackRepository, FindStudentByEmailUseCase findStudentByEmailUseCase, FindLessonByIdUseCase findLessonByIdUseCase) {
        this.customFeedbackRepository = customFeedbackRepository;
        this.findStudentByEmailUseCase = findStudentByEmailUseCase;
        this.findLessonByIdUseCase = findLessonByIdUseCase;
    }

    @Override
    public Feedback execute(Feedback feedback) {
        Lesson lesson = getLesson(feedback);
        User student = getStudent(feedback);
        feedback.setStudent(student);
        feedback.setLesson(lesson);
        return customFeedbackRepository.create(feedback);
    }

    private Lesson getLesson(Feedback feedback) {
        UUID lessonId = feedback.getLesson().getId();
        return findLessonByIdUseCase.execute(lessonId);
    }

    private User getStudent(Feedback feedback) {
        String email = feedback.getStudent().getEmail();
        return findStudentByEmailUseCase.execute(email);
    }
}
