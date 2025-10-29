package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity(name = "lesson")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JpaLessonEntity {

    @Id
    @UuidGenerator
    private UUID id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "lesson")
    private List<JpaFeedbackEntity> feedbacks;

}
