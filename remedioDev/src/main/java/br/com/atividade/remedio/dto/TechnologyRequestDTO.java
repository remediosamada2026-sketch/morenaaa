package br.com.atividade.remedio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TechnologyRequestDTO(
	@NotBlank(message = "O nome da tecnologia é obrigatório")
	@Size(min = 2, max = 50, message = "O nome deve ter entre 2 a 50 caracteres")
	String name
) {}