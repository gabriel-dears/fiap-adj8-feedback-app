package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in;

import fiap_adj8.feedback_platform.feedback_app.application.exception.OnlyStudentsCanCreateFeedbackException;
import fiap_adj8.feedback_platform.feedback_app.application.port.in.*;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Feedback;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.ApplicationPageDto;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.CreateFeedbackRequestDto;
import fiap_adj8.feedback_platform.feedback_app.infra.adapter.in.dto.FeedbackResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("feedback")
@Validated
public class FeedbackRestController {

    private final FindFeedbackByIdForAdminUseCase findFeedbackByIdForAdminUseCase;
    private final FindFeedbackByIdForStudentUseCase findFeedbackByIdForStudentUseCase;
    private final FindAllFeedbackForAdminUseCase findAllFeedbackForAdminUseCase;
    private final FindAllFeedbackForStudentUseCase findAllFeedbackForStudentUseCase;
    private final CreateFeedbackUseCase createFeedbackUseCase;
    private final AuthHelper authHelper;

    public FeedbackRestController(FindFeedbackByIdForAdminUseCase findFeedbackByIdForAdminUseCase, FindFeedbackByIdForStudentUseCase findFeedbackByIdForStudentUseCase, FindAllFeedbackForAdminUseCase findAllFeedbackForAdminUseCase, FindAllFeedbackForStudentUseCase findAllFeedbackForStudentUseCase, CreateFeedbackUseCase createFeedbackUseCase, AuthHelper authHelper) {
        this.findFeedbackByIdForAdminUseCase = findFeedbackByIdForAdminUseCase;
        this.findFeedbackByIdForStudentUseCase = findFeedbackByIdForStudentUseCase;
        this.findAllFeedbackForAdminUseCase = findAllFeedbackForAdminUseCase;
        this.findAllFeedbackForStudentUseCase = findAllFeedbackForStudentUseCase;
        this.createFeedbackUseCase = createFeedbackUseCase;
        this.authHelper = authHelper;
    }

    @GetMapping
    public ResponseEntity<ApplicationPageDto<FeedbackResponseDto>> findAllFeedback(@RequestParam(defaultValue = "10") @Max(50) int pageSize, @RequestParam(defaultValue = "0") @Min(0) int pageNumber) {
        boolean isAdminRole = authHelper.isAdminRole();
        ApplicationPageDto<FeedbackResponseDto> objResponse;
        if (isAdminRole) {
            objResponse = DtoFeedbackMapper.toPageDto(findAllFeedbackForAdminUseCase.execute(pageNumber, pageSize));
        } else {
            String email = authHelper.getEmail();
            objResponse = DtoFeedbackMapper.toPageDto(findAllFeedbackForStudentUseCase.execute(pageNumber, pageSize, email));

        }
        return ResponseEntity.ok(objResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDto> findFeedbackById(@PathVariable UUID id) {
        boolean isAdminRole = authHelper.isAdminRole();
        Feedback feedback;
        if (isAdminRole) {
            feedback = findFeedbackByIdForAdminUseCase.execute(id);
        } else {
            String email = authHelper.getEmail();
            feedback = findFeedbackByIdForStudentUseCase.execute(id, email);
        }
        return ResponseEntity.ok(DtoFeedbackMapper.toDto(feedback));
    }

    @PostMapping
    public ResponseEntity<FeedbackResponseDto> createFeedback(@RequestBody @Valid CreateFeedbackRequestDto createFeedbackRequestDto, UriComponentsBuilder uriComponentsBuilder) {
        boolean isStudentRole = authHelper.isStudentRole();
        if (!isStudentRole) {
            throw new OnlyStudentsCanCreateFeedbackException("Only students can create feedback");
        }
        String email = authHelper.getEmail();
        Feedback feedbackRequest = DtoFeedbackMapper.toDomain(createFeedbackRequestDto, email);
        Feedback feedbackAfterCreation = createFeedbackUseCase.execute(feedbackRequest);
        URI uri = uriComponentsBuilder.buildAndExpand(feedbackAfterCreation.getId()).toUri();
        return ResponseEntity.created(uri).body(DtoFeedbackMapper.toDto(feedbackAfterCreation));
    }

    // TODO: create unit tests -> using AI?
    // TODO: create integration tests - find all
    // TODO: create insomnia collection
}
