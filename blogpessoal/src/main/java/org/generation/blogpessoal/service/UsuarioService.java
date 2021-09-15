package org.generation.blogpessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogpessoal.model.UserLogin;
import org.generation.blogpessoal.model.usuario;
import org.generation.blogpessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service

public class UsuarioService {
 
	@Autowired
	private UsuarioRepository repository;
	
	
	public usuario cadastrarUsuario(usuario Usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder =encoder.encode(Usuario.getSenha());
		Usuario.setSenha(senhaEncoder);
		
		return repository.save(Usuario);
		
	}
	 
	 public Optional<UserLogin> logar(Optional<UserLogin> user){
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 Optional<usuario> Usuario =repository.findByEmail(user.get().getEmail());
		 
		 if(Usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(),Usuario.get().getSenha())) {
				
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth =Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader ="basic  "  + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setEmail(Usuario.get().getEmail());

				return user;
			}
			 
		 }
		
		 return null;
		 
		 
	 }
	
	
}
