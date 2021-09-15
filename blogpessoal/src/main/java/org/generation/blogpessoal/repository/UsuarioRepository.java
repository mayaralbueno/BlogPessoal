package org.generation.blogpessoal.repository;

import java.util.Optional;

import org.generation.blogpessoal.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<usuario, Long> {
	public Optional<usuario> findByEmail(String email);
	
	

}
