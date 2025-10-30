package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.CreateFeedbackUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindFeedbackByIdUseCase;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.CreateFeedbackRequestDto;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.FeedbackResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("feedback")
public class FeedbackRestController {

    private final FindFeedbackByIdUseCase findFeedbackByIdUseCase;
    private final CreateFeedbackUseCase createFeedbackUseCase;

    public FeedbackRestController(FindFeedbackByIdUseCase findFeedbackByIdUseCase, CreateFeedbackUseCase createFeedbackUseCase) {
        this.findFeedbackByIdUseCase = findFeedbackByIdUseCase;
        this.createFeedbackUseCase = createFeedbackUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDto> findFeedbackById(@PathVariable UUID id) {
        Feedback feedback = findFeedbackByIdUseCase.execute(id);
        return ResponseEntity.ok(DtoFeedbackMapper.toDto(feedback));
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDto> createFeedback(@RequestBody CreateFeedbackRequestDto createFeedbackRequestDto, UriComponentsBuilder uriComponentsBuilder) {
        Feedback feedbackRequest = DtoFeedbackMapper.toDomain(createFeedbackRequestDto);
        Feedback feedbackAfterCreation = createFeedbackUseCase.execute(feedbackRequest);
        URI uri = uriComponentsBuilder.buildAndExpand(feedbackAfterCreation.getId()).toUri();
        return ResponseEntity.created(uri).body(DtoFeedbackMapper.toDto(feedbackAfterCreation));
    }

    // TODO: create integration tests
}
