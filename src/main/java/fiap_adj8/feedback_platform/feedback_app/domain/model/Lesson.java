package fiap_adj8.feedback_platform.feedback_app.domain.model;

import java.util.List;
import java.util.UUID;

public class Lesson {

    private UUID id;
    private String name;
    private String description;
    private List<Feedback> feedbacks;
    public Lesson() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

}
