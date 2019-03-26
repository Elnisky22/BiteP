package model.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Adocao
 */
@Entity
public class Adocao implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@OneToOne
	private Usuario adotante;
	
	@Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date dataAdocao;
	
	@Id
	@GeneratedValue
	private Integer idAdocao;

	@OneToOne
	private Pet pet;

	

	public Adocao() {
	}

	public Adocao(Usuario adotante, Pet pet) {
		this.adotante = adotante;
		this.pet = pet;
	}
	
	public Usuario getAdotante() {
		return adotante;
	}
	public Date getDataAdocao() {
		return dataAdocao;
	}
	public Integer getIdAdocao() {
		return this.idAdocao;
	}
	public Pet getPet() {
		return pet;
	}
	public void setAdotante(Usuario adotante) {
		this.adotante = adotante;
	}
	public void setDataAdocao(Date dataAdocao) {
		this.dataAdocao = dataAdocao;
	}
	public void setIdAdocao(Integer idAdocao) {
		this.idAdocao = idAdocao;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}   
}
