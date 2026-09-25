package br.com.atividade.remedio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.atividade.remedio.dto.TechnologyResponseDTO;
import br.com.atividade.remedio.repository.TechnologyRepository;

@Service
public class TechnologyService {

	private final TechnologyRepository repository;

	TechnologyService(TechnologyRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(readOnly = true)
    public List<TechnologyResponseDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(TechnologyResponseDTO::fromEntity)
                .toList();
    }
}