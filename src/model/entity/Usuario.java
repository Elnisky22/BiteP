package model.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@Entity

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique = true)
	private String email;
	private String senha;
	private String nome;
	private String telefone;
	
	@OneToOne
	private Cidade cidade;
	@Column(columnDefinition = "DATE DEFAULT CURRENT_DATE", updatable=false, insertable=false)
	private Date dataCadastro;
	
	public Usuario() {
		super();
		this.cidade = new Cidade();
	}   
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		if(!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+[.[a-zA-Z]{2,}]+")) {
			throw(new IllegalArgumentException("email: " + email + " não é valido"));
		}else this.email = email;
	}   
	public String getSenha() {
		return this.senha;
	}
	public void setSenha(String senha) {
		System.out.println("teste senha setada");
		this.senha = senha;
	}
	public String getTelefone() {
		return this.telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}  
}
