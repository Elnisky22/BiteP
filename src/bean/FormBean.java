package bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import model.dao.CidadeDB;
import model.dao.EstadoDB;
import model.entity.Cidade;
import model.entity.Estado;



@ManagedBean(name = "form")
@ViewScoped
public class FormBean implements Serializable {

	@ManagedProperty(value="#{usuario}")
	private UsuarioBean usuario;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	@Inject
	EstadoDB edb;
	
	@Inject
	CidadeDB cdb;
	
	@PostConstruct
	protected void init() {
		this.estados = edb.listarEstado();
		System.out.println("tamanho da lista de estados : " + estados.size());
		carregarCidades();
	}	

	public void carregarCidades() {
		Integer idEstado = usuario.getUser().getCidade().getEstado().getId();
		if(idEstado != null) {
			System.out.println("valor do drop down: " + idEstado);
			this.cidades = cdb.listarCidade(idEstado);
			System.out.println("tamanho da lista cidades:" + cidades.size());
		}
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}
}