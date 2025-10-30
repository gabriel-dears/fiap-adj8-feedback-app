package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Lesson;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.CreateFeedbackRequestDto;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.FeedbackResponseDto;

public class DtoFeedbackMapper {
    public static FeedbackResponseDto toDto(Feedback feedback) {
        return new FeedbackResponseDto(
                feedback.getId(),
                feedback.getStudent(),
                feedback.getLesson(),
                feedback.getComment(),
                feedback.getRating(),
                feedback.getUrgent(),
                feedback.getDate()
        );
    }

    public static Feedback toDomain(CreateFeedbackRequestDto createFeedbackRequestDto) {
        Feedback feedback = new Feedback();
        feedback.setComment(createFeedbackRequestDto.comment());
        feedback.setRating(createFeedbackRequestDto.rating());
        feedback.setUrgent(createFeedbackRequestDto.urgent());
        feedback.setDate(createFeedbackRequestDto.date());
        User user = new User();
        user.setId(createFeedbackRequestDto.studentId());
        feedback.setStudent(user);
        Lesson lesson = new Lesson();
        lesson.setId(createFeedbackRequestDto.lessonId());
        feedback.setLesson(lesson);
        return feedback;
    }
}
