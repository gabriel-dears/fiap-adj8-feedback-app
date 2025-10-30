package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindLessonByIdUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomLessonRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.LessonNotFoundException;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.NullLessonIdException;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Lesson;

import java.util.UUID;

public class FindLessonByIdUseCaseImpl implements FindLessonByIdUseCase {

    private final CustomLessonRepository customLessonRepository;

    public FindLessonByIdUseCaseImpl(CustomLessonRepository customLessonRepository) {
        this.customLessonRepository = customLessonRepository;
    }

    @Override
    public Lesson execute(UUID lessonId) {
        if( lessonId == null ) {
            throw new NullLessonIdException("lessonId can't be null");
        }
        return customLessonRepository.findById(lessonId).orElseThrow(() -> new LessonNotFoundException(String.format("Lesson with id %s not found", lessonId)));
    }
}
