package br.com.atividade.remedio.dto;

import br.com.atividade.remedio.model.Technology;

public record TechnologyResponseDTO(Long id, String name) {
    
	public static TechnologyResponseDTO fromEntity(Technology tech) {
        return new TechnologyResponseDTO(tech.getId(), tech.getName());
    }
	
}