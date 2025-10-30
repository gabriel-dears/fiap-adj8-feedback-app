package fiap_adj8.feedback_platform.feedback_app.application.port.in;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Lesson;

import java.util.UUID;

public interface FindLessonByIdUseCase {
    Lesson execute(UUID lessonId);
}
