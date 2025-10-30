package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindStudentByEmailUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomUserRepository;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.NullStudentIdException;
import fiap_adj8.feedback_platform.feedback_app.domain.exception.UserNotFoundException;
import fiap_adj8.feedback_platform.feedback_app.domain.model.Role;
import fiap_adj8.feedback_platform.feedback_app.domain.model.User;

import java.util.Optional;

public class FindStudentByEmailUseCaseImpl implements FindStudentByEmailUseCase {

    private final CustomUserRepository customUserRepository;

    public FindStudentByEmailUseCaseImpl(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public User execute(String email) {
        if (email == null) {
            throw new NullStudentIdException("studentId can't be null");
        }
        return getStudent(email).orElseThrow(() -> new UserNotFoundException(String.format("Student with id %s not found", email)));
    }

    private Optional<User> getStudent(String email) {
        return customUserRepository.findByNameAndRole(email, Role.STUDENT);
    }
}
