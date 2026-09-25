package br.com.atividade.remedio.dto;

import br.com.atividade.remedio.model.Feedback;

public record FeedbackResponseDTO(
    Long id,
    Integer rating,
    String comment
) {
    public static FeedbackResponseDTO fromEntity(Feedback feedback) {
        return new FeedbackResponseDTO(feedback.getId(), feedback.getRating(), feedback.getComment());
    }
}
