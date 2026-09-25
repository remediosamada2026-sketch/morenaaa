
package br.com.atividade.remedio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.atividade.remedio.model.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Long>{
	
}
