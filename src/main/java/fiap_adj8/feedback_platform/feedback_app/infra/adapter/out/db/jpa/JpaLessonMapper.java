package fiap_adj8.feedback_platform.feedback_app.infra.adapter.out.db.jpa;

import fiap_adj8.feedback_platform.feedback_app.domain.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class JpaLessonMapper {

    public Lesson toModel(JpaLessonEntity jpaLessonEntity) {
        Lesson lesson = new Lesson();
        lesson.setId(jpaLessonEntity.getId());
        lesson.setDescription(jpaLessonEntity.getDescription());
        return lesson;
    }

    public JpaLessonEntity toEntity(Lesson lesson) {
        JpaLessonEntity entity = new JpaLessonEntity();
        entity.setId(lesson.getId());
        entity.setDescription(lesson.getDescription());
        return entity;
    }
}
