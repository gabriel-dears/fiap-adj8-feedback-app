package fiap_adj8.feedback_platform.feedback_app.infra.exception;

import fiap_adj8.feedback_platform.feedback_app.application.exception.OnlyStudentsCanCreateFeedbackException;
import fiap_adj8.feedback_platform.feedback_app.application.exception.StudentHasAlreadyCreatedFeedbackForThatLesson;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.*;
import fiap_adj8.feedback_platform.feedback_app.infra.exception.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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
            NullStudentIdException.class,
            StudentHasAlreadyCreatedFeedbackForThatLesson.class
    })
    public ResponseEntity<ErrorResponseDto> handleInputErrors(RuntimeException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponseDto error = new ErrorResponseDto(status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler({
            OnlyStudentsCanCreateFeedbackException.class
    })
    public ResponseEntity<ErrorResponseDto> handleForbiddenErrors(RuntimeException ex) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ErrorResponseDto error = new ErrorResponseDto(status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                errors.toString()
        ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleJsonParseException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Invalid JSON format or enum value");
        return ResponseEntity.badRequest().body(new ErrorResponseDto(
                HttpStatus.BAD_REQUEST.value(),
                errors.toString()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericExceptions(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponseDto error = new ErrorResponseDto(status.value(), ex.getMessage());
        return ResponseEntity.status(status).body(error);
    }

}
