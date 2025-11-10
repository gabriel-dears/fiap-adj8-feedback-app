package fiap_adj8.feedback_platform.feedback_app.domain.model;

public class LessonFeedbackSummary {

    private String lessonName;
    private Long totalFeedbacks;
    private Double averageRating;

    public LessonFeedbackSummary(String lessonName, Long totalFeedbacks, Double averageRating) {
        this.lessonName = lessonName;
        this.totalFeedbacks = totalFeedbacks;
        this.averageRating = averageRating;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Long getTotalFeedbacks() {
        return totalFeedbacks;
    }

    public void setTotalFeedbacks(Long totalFeedbacks) {
        this.totalFeedbacks = totalFeedbacks;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
