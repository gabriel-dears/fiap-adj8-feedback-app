package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindStudentByIdUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomUserRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.NullStudentIdException;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.UserNotFoundException;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Role;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public class FindStudentByIdUseCaseImpl implements FindStudentByIdUseCase {

    private final CustomUserRepository customUserRepository;

    public FindStudentByIdUseCaseImpl(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public User execute(UUID studentId) {
        if (studentId == null) {
            throw new NullStudentIdException("studentId can't be null");
        }
        return getStudent(studentId).orElseThrow(() -> new UserNotFoundException(String.format("Student with id %s not found", studentId)));
    }

    private Optional<User> getStudent(UUID studentId) {
        return customUserRepository.findByIdAndRole(studentId, Role.STUDENT);
    }
}
