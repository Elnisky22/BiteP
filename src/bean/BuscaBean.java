package bean;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import model.dao.PetDB;
import model.entity.Pet;

@ManagedBean(name = "busca")
@SessionScoped
public class BuscaBean implements Serializable {
	
	@ManagedProperty(value="#{usuario}")
	private UsuarioBean usuario;	
	
	private String nome;
	private String raca;
	private Boolean bCat = true;
	private Boolean bDog = true;
	private Boolean bFem = true;
	private Boolean bMasc = true;
	protected List<Pet> resultPet;
	
	@Inject
	PetDB petdb;
	
	
	public String buscarPet() {	
		if(nome.isEmpty() && raca.isEmpty() && bDog == false && bCat == false && bMasc == false && bFem == false) {
			System.out.println("\n--> Não Foi Inserido Informações!!\n");
			return null; // <--- algum aviso ao user!
		}else {
			this.resultPet = petdb.retornarPetsBusca(nome,raca,bDog,bCat,bMasc,bFem);
			if(resultPet.isEmpty()) {
				System.out.println("Não houve Resultados");
			}else {
				for(Pet p: resultPet) {
					System.out.println("P -> "+p.getNome());
				}
			}
		}
		System.out.println("Consulta Concluida com Sucesso!");
		
		usuario.setProxPagina("buscaPet.faces");
		return "index";
	}

	//GETTERS e SETTERS
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public void setResultPet(List<Pet> resultPet) {
		this.resultPet = resultPet;
	}
	public List<Pet> getResultPet() {
		return resultPet;
	}
	public boolean isbCat() {
		return bCat;
	}
	public void setbCat(boolean bCat) {
		this.bCat = bCat;
	}
	public boolean isbDog() {
		return bDog;
	}
	public void setbDog(boolean bDog) {
		this.bDog = bDog;
	}
	public boolean isbFem() {
		return bFem;
	}
	public void setbFem(boolean bFem) {
		this.bFem = bFem;
	}
	public boolean isbMasc() {
		return bMasc;
	}
	public void setbMasc(boolean bMasc) {
		this.bMasc = bMasc;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}
}
