package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomFeedbackRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.FeedbackNotFoundException;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Lesson;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Rating;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindFeedbackByIdUseCaseImplTest {

    @Mock
    CustomFeedbackRepository customFeedbackRepository;

    @InjectMocks
    FindFeedbackByIdUseCaseImpl findFeedbackByIdUseCase;

    @Test
    void shouldNotFindFeedbackById() {
        UUID randomUUID = UUID.randomUUID();
        when(customFeedbackRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(FeedbackNotFoundException.class, () -> findFeedbackByIdUseCase.execute(randomUUID));
    }

    @Test
    void shouldFindFeedbackById() {
        UUID randomUUID = UUID.randomUUID();
        Feedback feedbackToBeRetrieved = new Feedback();
        LocalDateTime now = LocalDateTime.now();
        feedbackToBeRetrieved.setDate(now);
        feedbackToBeRetrieved.setId(randomUUID);
        feedbackToBeRetrieved.setUrgent(true);
        feedbackToBeRetrieved.setRating(Rating.FIVE);
        feedbackToBeRetrieved.setStudent(new User());
        feedbackToBeRetrieved.setLesson(new Lesson());
        feedbackToBeRetrieved.setComment("Random comment");
        when(customFeedbackRepository.findById(any(UUID.class))).thenReturn(Optional.of(feedbackToBeRetrieved));
        Feedback feedback = findFeedbackByIdUseCase.execute(randomUUID);
        assertNotNull(feedback);
        assertEquals("Random comment", feedback.getComment());
        assertEquals(now, feedback.getDate());
        assertEquals(Rating.FIVE, feedback.getRating());
        assertEquals(User.class, feedback.getStudent().getClass());
        assertEquals(Lesson.class, feedback.getLesson().getClass());
        assertEquals(randomUUID, feedback.getId());
    }

}