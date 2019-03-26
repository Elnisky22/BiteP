package model.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Pet
 *
 */
@Entity

public class Pet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private char genero;
	private String especie;
	private String raca;
	private String observacao;

	@OneToOne
	@JoinColumn(nullable = false) //não e possivel existir um pet sem dono para cadastralo
	private Usuario dono;

	@Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date dataNascimento;
	
	@Column(columnDefinition = "DATE DEFAULT CURRENT_DATE", updatable=false, insertable=false)
	private Date dataRegistro;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="pet_id") //nome da coluna da FK na tabela de imagens
	private List<Imagem> fotos= new ArrayList<Imagem>(4);

	public Pet() {
		super();
	}

	public int getIdade() {
	
		int anoNascimento = dataNascimento.toLocalDate().getYear();
		int anoPresente = LocalDate.now().getYear();
		return anoPresente - anoNascimento;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		System.out.println("set nome pet chamado");
		this.nome = nome;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public List<Imagem> getFotos() {
		return fotos;
	}

	public void setFotos(List<Imagem> fotos) {
		this.fotos = fotos;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
