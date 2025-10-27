package fiap_adj8.feedback_platform.feedback_app.domain.model;

import java.time.LocalDateTime;

public class Feedback {

    private Long id;
    private User student;
    private Lesson lesson;
    private String comment;
    private Rating rating;
    private Boolean urgent;
    private LocalDateTime date;

    public Feedback() {
    }

    public Feedback(Long id, User student, Lesson lesson, String comment, Rating rating, Boolean urgent, LocalDateTime date) {
        this.id = id;
        this.student = student;
        this.lesson = lesson;
        this.comment = comment;
        this.rating = rating;
        this.urgent = urgent;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
