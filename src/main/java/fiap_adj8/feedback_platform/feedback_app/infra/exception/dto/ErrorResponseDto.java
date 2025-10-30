package fiap_adj8.feedback_platform.feedback_app.infra.exception.dto;

public record ErrorResponseDto(
        int status,
        String message
) {
}
