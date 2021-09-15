package org.generation.blogpessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;


@Entity
@Table(name ="tb_tema")

public class tema {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long id;
	 @NotNull
	private String descricao;
	 @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
	 @JsonIncludeProperties("tema")
	private List<postagem> postagem;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<postagem> getPostagem() {
		return postagem;
	}
	public void setPostagem(List<postagem> postagem) {
		this.postagem = postagem;
	}
	
	

}
