package org.generation.blogpessoal.service;

import java.util.Optional;

import org.generation.blogpessoal.model.Tema;
import org.generation.blogpessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TemaService {
	 
	private @Autowired TemaRepository repository;
	
	/**
     * Método utilizado para alterar um tema que retorna um Optional com Tema caso
     * correto ou um Optional.empyt() caso id do tema não exista.
     * 
     * @param temaParaAlterar do tipo Tema
     * @return Optional com Tema alterado
     * @since 1.0
     * @author Turma 29
     * 
     */
	
	public Optional<Tema> atualizarTema(Tema descricaoParaAlterar) {
		return repository.findById(descricaoParaAlterar.getId()).map(temaExistente -> {
			temaExistente.setDescricao(descricaoParaAlterar.getDescricao());
			return Optional.ofNullable(repository.save(temaExistente));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}
