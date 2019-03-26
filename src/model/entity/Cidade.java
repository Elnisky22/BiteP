package model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cidade
 * 
 */
@Entity

public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String nome;

	@ManyToOne
	private Estado estado;
	
	public Cidade() {
		super();
		this.estado = new Estado();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}      
	public Estado getEstado() {
		return estado;
	}
}
