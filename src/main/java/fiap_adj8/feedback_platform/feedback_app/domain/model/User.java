package fiap_adj8.feedback_platform.feedback_app.domain.model;

import java.util.List;

public class User {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private List<Feedback> feedbacks;

    public User() {}

    public User(Long id, String name, String email, Role role, List<Feedback> feedbacks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.feedbacks = feedbacks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

}
