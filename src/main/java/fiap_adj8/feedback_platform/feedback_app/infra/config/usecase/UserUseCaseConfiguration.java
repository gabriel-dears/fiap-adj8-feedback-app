package fiap_adj8.feedback_platform.feedback_app.infra.config.usecase;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindAllAdminEmailsUseCase;
import fiap_adj8.feedback_platform.feedback_app.application.service.FindAllAdminEmailsUseCaseImpl;
import fiap_adj8.feedback_platform.feedback_app.application.port.out.CustomUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfiguration {

    @Bean
    public FindAllAdminEmailsUseCase findAllAdminEmails(CustomUserRepository customUserRepository) {
        return new FindAllAdminEmailsUseCaseImpl(customUserRepository);
    }

}
