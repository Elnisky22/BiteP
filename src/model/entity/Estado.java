package model.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Estado
 *
 */
@Entity

public class Estado implements Serializable {

	   
	@Id
	private Integer id;
	private String nome;
	private static final long serialVersionUID = 1L;

	public Estado() {
		super();
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
}
