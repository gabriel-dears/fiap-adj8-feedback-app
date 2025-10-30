package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.CreateFeedbackUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindFeedbackByIdForAdminUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindFeedbackByIdForStudentUseCase;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.CreateFeedbackRequestDto;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.FeedbackResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("feedback")
public class FeedbackRestController {

    private final FindFeedbackByIdForAdminUseCase findFeedbackByIdForAdminUseCase;
    private final FindFeedbackByIdForStudentUseCase findFeedbackByIdForStudentUseCase;
    private final CreateFeedbackUseCase createFeedbackUseCase;

    public FeedbackRestController(FindFeedbackByIdForAdminUseCase findFeedbackByIdForAdminUseCase, FindFeedbackByIdForStudentUseCase findFeedbackByIdForStudentUseCase, CreateFeedbackUseCase createFeedbackUseCase) {
        this.findFeedbackByIdForAdminUseCase = findFeedbackByIdForAdminUseCase;
        this.findFeedbackByIdForStudentUseCase = findFeedbackByIdForStudentUseCase;
        this.createFeedbackUseCase = createFeedbackUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDto> findFeedbackById(@PathVariable UUID id, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        Feedback feedback;

        if (isAdmin) {
            feedback = findFeedbackByIdForAdminUseCase.execute(id);
        } else {
            feedback = findFeedbackByIdForStudentUseCase.execute(id, authentication.getName());
        }

        return ResponseEntity.ok(DtoFeedbackMapper.toDto(feedback));
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDto> createFeedback(@RequestBody CreateFeedbackRequestDto createFeedbackRequestDto, UriComponentsBuilder uriComponentsBuilder, Authentication authentication) {
        boolean isStudent = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"));
        if (!isStudent) {
            throw new RuntimeException("Only students can create feedback");
        }
        String email = authentication.getName();
        Feedback feedbackRequest = DtoFeedbackMapper.toDomain(createFeedbackRequestDto, email);
        Feedback feedbackAfterCreation = createFeedbackUseCase.execute(feedbackRequest);
        URI uri = uriComponentsBuilder.buildAndExpand(feedbackAfterCreation.getId()).toUri();
        return ResponseEntity.created(uri).body(DtoFeedbackMapper.toDto(feedbackAfterCreation));
    }

    // TODO: create find all
    // TODO: create unit tests -> using AI?
    // TODO: create integration tests - find all
    // TODO: create insomnia collection
}
