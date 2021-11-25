package org.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.startup.ClassLoaderFactory.RepositoryType;
import org.generation.blogpessoal.model.Tema;
import org.generation.blogpessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/tema")
public class TemaController {

	@Autowired
	private TemaRepository repository;

	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{tema_id}")
	public ResponseEntity<Tema> getByid(@PathVariable(value = "tema_id") long id) {
		return repository.findById(id).map(res -> ResponseEntity.ok(res)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByname(@PathVariable(value = "nome") String nome) {
		return ResponseEntity.ok(repository.findByDescricaoContainingIgnoreCase(nome));
	}

	@PostMapping("/salvar")
	public ResponseEntity<Tema> post(@Valid @RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));

	}

	@PutMapping("/atualizar")
	public ResponseEntity<Tema> put(@RequestBody Tema tema) {
		return ResponseEntity.ok(repository.save(tema));
	}

	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable(value = "id") long id) {
		repository.deleteById(id);

	}
}
