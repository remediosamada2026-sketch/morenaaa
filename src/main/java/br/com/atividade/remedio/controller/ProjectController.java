package br.com.atividade.remedio.controller;

import br.com.atividade.remedio.dto.FeedbackRequestDTO;
import br.com.atividade.remedio.dto.FeedbackResponseDTO;
import br.com.atividade.remedio.dto.ProjectResponseDTO;
import br.com.atividade.remedio.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@Tag(name = "Projects", description = "Endpoints de gerenciamento e engajamento de projetos")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/{id}/feedbacks")
    @Operation(summary = "Cadastrar feedback (nota de 1 a 5 e comentário) e recalcular média do projeto")
    public ResponseEntity<FeedbackResponseDTO> addFeedback(
            @PathVariable Long id,
            @RequestBody @Valid FeedbackRequestDTO request
    ) {
        FeedbackResponseDTO response = projectService.addFeedback(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/upvote")
    @Operation(summary = "Incrementar curtidas/estrelas do projeto")
    public ResponseEntity<ProjectResponseDTO> upvoteProject(@PathVariable Long id) {
        ProjectResponseDTO response = projectService.upvote(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar projetos com suporte a filtro por tecnologia e paginação")
    public ResponseEntity<Page<ProjectResponseDTO>> listProjects(
            @RequestParam(required = false) String technology,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        Page<ProjectResponseDTO> response = projectService.listProjects(technology, pageable);
        return ResponseEntity.ok(response);
    }
}


