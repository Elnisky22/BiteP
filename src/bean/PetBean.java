package bean;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;

import model.adapter.AdapterPart;
import model.dao.PetDB;
import model.entity.Imagem;
import model.entity.Pet;

@ManagedBean(name = "petBean")
@SessionScoped
public class PetBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Pet pet;
	private String dataNascimento;
	private Part img1;
	private Part img2;
	private Part img3;
	private Part img4;
	
	@Inject
	private PetDB petDb;
	
	@ManagedProperty(value="#{usuario}")
	private UsuarioBean usuario;
	
	private List<Pet> listaPets;
	private List<Pet> todosPets;
	
	@PostConstruct
	private void init() {
		todosPets = petDb.retornarTodosPets();
		pet = new Pet();
	}
	
	public String carregarMeusPets() {
		if(usuario.isLogado()) {
			listaPets = petDb.retornarPetsUsuario(usuario.getUser().getId());
			usuario.setProxPagina("meusPets.faces");
			return "index";
		}else {
			return "index";
		}
	}
	
	public String carregarHome() {
		todosPets = petDb.retornarTodosPets();
		usuario.setProxPagina("home.faces");
		return "index";
	}
	
	public String iniciarPaginaCadastro() {
		pet = new Pet();
		usuario.setProxPagina("cadastrarPet.faces");
		return "index";
	}
	
	public String cadastrarPet() {
		
		pet.setDono(usuario.getUser());
		List<Imagem> fotosPet = pet.getFotos();
		fotosPet.add(new Imagem(new AdapterPart(img1).getConteudo()));
		fotosPet.add(new Imagem(new AdapterPart(img2).getConteudo())); 
		fotosPet.add(new Imagem(new AdapterPart(img3).getConteudo()));
		fotosPet.add(new Imagem(new AdapterPart(img4).getConteudo()));
		try {		
			System.out.println("Data de nascimento:" + dataNascimento);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(dataNascimento);
			pet.setDataNascimento(new java.sql.Date(date.getTime()));
			petDb.cadastrar(pet);
			return carregarMeusPets();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	public String visualizarPet(Integer idPet) {
		try {
			System.out.println("abrindo perfil do pet" + idPet);
			this.pet = petDb.consultarID(Pet.class, idPet);
			usuario.setProxPagina("pet.faces");
			return "index";
		}catch (Exception e) {
			System.out.println("Falha ao vizualisar pet");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String excluirPet(Integer idExcluir) {
		System.out.println("Excluindo pet -> "+idExcluir);
		try{
			petDb.remover(petDb.consultarID(Pet.class, idExcluir)); 
			todosPets = petDb.retornarTodosPets();
			return carregarMeusPets();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	//GETTERS e SETTERS
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	public Part getImg1() {
		return img1;
	}
	public void setImg1(Part img1) {
		this.img1 = img1;
	}
	public Part getImg2() {
		return img2;
	}
	public void setImg2(Part img2) {
		this.img2 = img2;
	}
	public Part getImg3() {
		return img3;
	}
	public void setImg3(Part img3) {
		this.img3 = img3;
	}
	public Part getImg4() {
		return img4;
	}
	public void setImg4(Part img4) {
		this.img4 = img4;
	}
	public UsuarioBean getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}
	public List<Pet> getListaPets() {
		return listaPets;
	}
	public void setListaPets(List<Pet> listaPets) {
		this.listaPets = listaPets;
	}
	public void setTodosPets(List<Pet> todosPets){
		this.todosPets = todosPets;
	}
	public List<Pet> getTodosPets(){
		return todosPets;
	}
}
