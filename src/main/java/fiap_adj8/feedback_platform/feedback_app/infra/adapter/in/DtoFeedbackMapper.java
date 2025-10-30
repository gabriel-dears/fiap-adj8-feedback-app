package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in;

import fiap_adj8.feedback_platform.feedback_app.application.model.ApplicationPage;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Lesson;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.ApplicationPageDto;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.CreateFeedbackRequestDto;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.FeedbackResponseDto;

public class DtoFeedbackMapper {
    public static FeedbackResponseDto toDto(Feedback feedback) {
        return new FeedbackResponseDto(
                feedback.getId(),
                feedback.getStudent().getId(),
                feedback.getLesson().getId(),
                feedback.getComment(),
                feedback.getRating(),
                feedback.getUrgent(),
                feedback.getDate()
        );
    }

    public static Feedback toDomain(CreateFeedbackRequestDto createFeedbackRequestDto, String email) {
        Feedback feedback = new Feedback();
        feedback.setComment(createFeedbackRequestDto.comment());
        feedback.setRating(createFeedbackRequestDto.rating());
        feedback.setUrgent(createFeedbackRequestDto.urgent());
        User user = new User();
        user.setEmail(email);
        feedback.setStudent(user);
        Lesson lesson = new Lesson();
        lesson.setId(createFeedbackRequestDto.lessonId());
        feedback.setLesson(lesson);
        return feedback;
    }

    public static ApplicationPageDto<FeedbackResponseDto> toPageDto(ApplicationPage<Feedback> execute) {
        return new ApplicationPageDto<>(
                execute.getItems().stream().map(DtoFeedbackMapper::toDto).toList(),
                execute.getPage(),
                execute.getSize(),
                execute.getTotalElements(),
                execute.getTotalPages(),
                execute.isLast(),
                execute.isFirst()
        );
    }
}
