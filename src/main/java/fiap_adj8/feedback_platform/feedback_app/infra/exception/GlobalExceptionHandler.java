package fiap_adj8.feedback_platform.feedback_app.infra.exception;

import fiap_adj8.feedback_platform.feedback_app.domain.exception.*;
import fiap_adj8.feedback_platform.feedback_app.infra.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            FeedbackNotFoundException.class,
            LessonNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDto> handleNotFoundExceptions(RuntimeException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponseDto error = new ErrorResponseDto(status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler({
            NullLessonIdException.class,
            NullStudentIdException.class
    })
    public ResponseEntity<ErrorResponseDto> handleInputErrors(RuntimeException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDto error = new ErrorResponseDto(status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericExceptions(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponseDto error = new ErrorResponseDto(status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(error);
    }

}
