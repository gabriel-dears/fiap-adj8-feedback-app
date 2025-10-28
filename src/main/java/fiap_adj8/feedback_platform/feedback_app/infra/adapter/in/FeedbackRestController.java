package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindFeedbackByIdUseCase;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.FeedbackResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("feedback")
public class FeedbackRestController {

    private final FindFeedbackByIdUseCase findFeedbackByIdUseCase;

    public FeedbackRestController(FindFeedbackByIdUseCase findFeedbackByIdUseCase) {
        this.findFeedbackByIdUseCase = findFeedbackByIdUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDto> findFeedbackById(@PathVariable UUID id) {
        Feedback feedback = findFeedbackByIdUseCase.execute(id);
        return ResponseEntity.ok(DtoFeedbackMapper.toDto(feedback));
    }

    // TODO: integrate with db
    // TODO: create integration tests
    // TODO: create CRUD

}
