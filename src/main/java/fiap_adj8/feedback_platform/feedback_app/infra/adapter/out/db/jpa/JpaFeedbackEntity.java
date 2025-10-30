package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Rating;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "feedback")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JpaFeedbackEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private JpaUserEntity student;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private JpaLessonEntity lesson;

    private String comment;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rating rating;

    @NotNull
    private Boolean urgent;

    private LocalDateTime date;

    @PreUpdate
    public void preUpdate() {
        this.date = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        this.date = LocalDateTime.now();
    }

}
