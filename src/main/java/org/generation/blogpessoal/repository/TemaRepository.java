package org.generation.blogpessoal.repository;

import java.util.List;

import org.generation.blogpessoal.model.tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<tema,Long> {
    public List<tema>findBydescricaoContainingIgnoreCase(String descricao);
}
