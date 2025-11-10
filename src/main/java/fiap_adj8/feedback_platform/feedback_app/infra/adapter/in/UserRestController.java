package fiap_adj8.feedback_platform.feedback_app.infra.adapter.in;

import fiap_adj8.feedback_platform.feedback_app.application.port.in.FindAllAdminEmailsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final FindAllAdminEmailsUseCase findAllAdminEmailsUseCase;

    public UserRestController(FindAllAdminEmailsUseCase findAllAdminEmailsUseCase) {
        this.findAllAdminEmailsUseCase = findAllAdminEmailsUseCase;
    }

    @GetMapping("/admin/email")
    ResponseEntity<List<String>> getAllEmails() {
        return ResponseEntity.ok().body(findAllAdminEmailsUseCase.findAllAdminEmails());
    }

}
