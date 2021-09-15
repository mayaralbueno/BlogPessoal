package org.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.blogpessoal.model.UserLogin;
import org.generation.blogpessoal.model.usuario;
import org.generation.blogpessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService UsuarioService;
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user){
		return UsuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
		    .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	 @PostMapping("/cadastrar")
	 public ResponseEntity<usuario> Post(@RequestBody usuario Usuario){
		 return ResponseEntity.status(HttpStatus.CREATED)
		 .body(UsuarioService.cadastrarUsuario(Usuario));
	 }
	 	

}
