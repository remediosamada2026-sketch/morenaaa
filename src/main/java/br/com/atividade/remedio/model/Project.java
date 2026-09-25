package br.com.atividade.remedio.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String technology;

    @Column(nullable = false)
    private Long upvotes = 0L;

    @Column(nullable = false)
    private Double averageRating = 0.0;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feedback> feedbacks = new ArrayList<>();

    public Project() {}

    public Project(String name, String technology) {
        this.name = name;
        this.technology = technology;
        this.upvotes = 0L;
        this.averageRating = 0.0;
    }

    public void recalculateAverageRating() {
        if (feedbacks == null || feedbacks.isEmpty()) {
            this.averageRating = 0.0;
            return;
        }
        double sum = feedbacks.stream().mapToInt(Feedback::getRating).sum();
        this.averageRating = Math.round((sum / feedbacks.size()) * 100.0) / 100.0;
    }

    public void incrementUpvote() {
        this.upvotes++;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTechnology() { return technology; }
    public void setTechnology(String technology) { this.technology = technology; }

    public Long getUpvotes() { return upvotes; }
    public void setUpvotes(Long upvotes) { this.upvotes = upvotes; }

    public Double getAverageRating() { return averageRating; }
    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }

    public List<Feedback> getFeedbacks() { return feedbacks; }
    public void setFeedbacks(List<Feedback> feedbacks) { this.feedbacks = feedbacks; }
}

