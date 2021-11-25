package org.generation.blogpessoal.service;

import java.util.Optional;

import org.generation.blogpessoal.model.Postagem;
import org.generation.blogpessoal.model.Tema;
import org.generation.blogpessoal.model.Usuario;
import org.generation.blogpessoal.repository.Postagemrepository;
import org.generation.blogpessoal.repository.TemaRepository;
import org.generation.blogpessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service

public class PostagemService {
	private @Autowired Postagemrepository repositorioP;
	private @Autowired UsuarioRepository repositorioU;
	private @Autowired TemaRepository repositorioT;

	/**
	 * Método utilizado para alterar uma postagem que retorna um Optional com
	 * Postagem caso corretoou um Optional.empty() caso id da Postagem não exista.
	 * 
	 * @param postagemParaAlterar do tipo Postagem
	 * @return Optional com Postagem alterada
	 * @since 2.0
	 * @author Turma 29
	 */
	  
	public Optional<?> atualizarPostagem(Postagem postagemParaAlterar) {
		return repositorioP.findById(postagemParaAlterar.getId()).map(postagemExistente -> {
			Optional<Usuario> optionalUsuario = repositorioU.findById(postagemParaAlterar.getCriador().getId());
			Optional<Tema> optionalTema = repositorioT.findById(postagemParaAlterar.getTema().getId());
			if (optionalUsuario.isPresent() && optionalTema.isPresent()) {
				postagemExistente.setTitulo(postagemParaAlterar.getTitulo());
				postagemExistente.setTexto(postagemParaAlterar.getTexto());
				return Optional.ofNullable(repositorioP.save(postagemExistente));
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	/**
	 * Método usado no cadastro de uma nova postagem dentro do banco, validando se o
	 * usuario criador é existente. O id do usuario criador e o id do tema devem ser
	 * repassados dentro do objeto postagem para que a devida criação seja efetuada.
	 * Caso o id do usuario não for passado ou não existir no banco, há retorno de
	 * um Optional.empty()
	 * 
	 * @param novaPostagem do tipo Postagem
	 * @return Optional com Postagem
	 * @since 1.5
	 * @author 
	 */
	public Optional<?> cadastrarPostagem(Postagem novaPostagem) {
		Optional<Tema> objetoExistente = repositorioT.findById(novaPostagem.getTema().getId());
		return repositorioU.findById(novaPostagem.getCriador().getId()).map(usuarioExistente -> {
			if (objetoExistente.isPresent()) {
				novaPostagem.setCriador(usuarioExistente);
				novaPostagem.setTema(objetoExistente.get());
				return Optional.ofNullable(repositorioP.save(novaPostagem));
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

}


