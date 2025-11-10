package fiap_adj8.feedback_platform.feedback_app.application.service;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindAllAdminEmailsUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomUserRepository;

import java.util.List;

public class FindAllAdminEmailsUseCaseImpl implements FindAllAdminEmailsUseCase {

    private final CustomUserRepository customUserRepository;

    public FindAllAdminEmailsUseCaseImpl(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public List<String> findAllAdminEmails() {
        return customUserRepository.findAllAdminEmails();
    }
}
