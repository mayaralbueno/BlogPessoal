package org.generation.blogpessoal.controller;

import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.RepositoryType;
import org.generation.blogpessoal.model.postagem;
import org.generation.blogpessoal.repository.postagemrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping ("/postagens")
@CrossOrigin("*")
public class postagemcontroller {
	
	@Autowired
	private postagemrepository repositoty;

	@GetMapping
    public ResponseEntity<List<postagem>> GettAll(){
    	return ResponseEntity.ok(repositoty.findAll());
    	
    }
 
	@GetMapping("/{id}")
	public ResponseEntity<postagem> Getbyid(@PathVariable (value ="id")long id){
		return repositoty.findById(id).map(resp ->ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<postagem>> GetBytitulo(@PathVariable(value = "titulo") String titulo){
		return ResponseEntity.ok(repositoty.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping("/salvar")
	public  ResponseEntity<postagem> post (@RequestBody  postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED) .body(repositoty.save(postagem));
		
	}
	@PutMapping("/atualizar")
	public  ResponseEntity<postagem> Put (@RequestBody  postagem postagem){
		return ResponseEntity.status(HttpStatus.OK) .body(repositoty.save(postagem));
	}
	  @DeleteMapping("/{id}")
	  public void delete(@PathVariable(value ="id") long id ) {
		repositoty.deleteById(id);
		  
	  }
	  
}
