package org.generation.blogpessoal.repository;

import java.util.List;

import org.generation.blogpessoal.model.postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface postagemrepository  extends JpaRepository<postagem, Long> {
	public List<postagem> findAllByTituloContainingIgnoreCase (String titulo);
}
