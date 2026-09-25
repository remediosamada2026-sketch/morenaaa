package br.com.atividade.remedio.service;

import br.com.atividade.remedio.dto.FeedbackRequestDTO;
import br.com.atividade.remedio.dto.FeedbackResponseDTO;
import br.com.atividade.remedio.dto.ProjectResponseDTO;
import br.com.atividade.remedio.model.Feedback;
import br.com.atividade.remedio.model.Project;
import br.com.atividade.remedio.repository.FeedbackRepository;
import br.com.atividade.remedio.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final FeedbackRepository feedbackRepository;

    public ProjectService(ProjectRepository projectRepository, FeedbackRepository feedbackRepository) {
        this.projectRepository = projectRepository;
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public FeedbackResponseDTO addFeedback(Long projectId, FeedbackRequestDTO dto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado com id: " + projectId));

        Feedback feedback = new Feedback(dto.rating(), dto.comment(), project);
        project.getFeedbacks().add(feedback);
        
        project.recalculateAverageRating();
        
        Feedback savedFeedback = feedbackRepository.save(feedback);
        projectRepository.save(project);

        return FeedbackResponseDTO.fromEntity(savedFeedback);
    }

    @Transactional
    public ProjectResponseDTO upvote(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado com id: " + projectId));

        project.incrementUpvote();
        Project updated = projectRepository.save(project);

        return ProjectResponseDTO.fromEntity(updated);
    }

    @Transactional(readOnly = true)
    public Page<ProjectResponseDTO> listProjects(String technology, Pageable pageable) {
        Page<Project> page;
        if (technology != null && !technology.isBlank()) {
            page = projectRepository.findByTechnologyIgnoreCase(technology, pageable);
        } else {
            page = projectRepository.findAll(pageable);
        }
        return page.map(ProjectResponseDTO::fromEntity);
    }
}
