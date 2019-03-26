package bean;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import model.dao.CidadeDB;
import model.dao.UsuarioDB;
import model.entity.Cidade;
import model.entity.Usuario;

@ManagedBean(name = "usuario")
@SessionScoped
public class UsuarioBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String proxPagina = "home.faces";

	private Boolean logado;
	private String senha2;	
	private Usuario user;
	
	@Inject
	UsuarioDB userDb;
	
	@Inject
	CidadeDB cidade;
	
	@PostConstruct
	private void init() {
		user = new Usuario();
		logado = false;
	}
	
	public String logar() {
		Usuario resp;
		resp = (Usuario) userDb.validarLogin(user.getEmail(),user.getSenha());
		if(resp != null) {
			logado = true;
			user = resp;
			System.out.println("usuario logado com sucesso");
			return "index"; 
		}
		else {
			return null;
		}
	}
	
	public String logout() {
		user = new Usuario();
		logado = false;
		setProxPagina("home.faces");
		return "index";
	}

	public String cadastrarUsuario(){
		System.out.println("Chamado metodo Cadastrar!");
		try {
			if(!user.getSenha().equals(senha2)) {             
				System.out.println("Senhas Diferentes"); 
				return null;
			}
			user.setCidade(cidade.consultarID(Cidade.class, user.getCidade().getId()));
			userDb.cadastrar(user);
			logado = true;
			return "index";
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	
	public String atualizarInformacoes() {
		System.out.println("Atualizando dados...");
		try {
			if(logado) {
				userDb.alterar(user);
				user = userDb.consultarID(Usuario.class, user.getId());
				System.out.println("Tudo certo!!");
				return "index";
			}else {
				System.out.println("usuario nao esta logado");
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String cancelarAtualizarInfos() {
		System.out.println("Atualizando dados...");
		try {
			user = userDb.consultarID(Usuario.class, user.getId());
			System.out.println("atualização cancelada!!");
			return "index";
		}catch(Exception e) {
			System.out.println("problema ao cancelar atualização");
			e.printStackTrace();
			return null;
		}
	}
	
	public String excluirConta() {
		try{
			if(logado) {
				userDb.remover(user);      //GERA EXCESSAO SE TIVER PETS CADASTRADOS! CHAVE EXTRANGEIRA!
				user = new Usuario();
				logado = false;
				System.out.println("Conta excluida com sucesso!");
				return "index";
			}else {
				System.out.println("Não Foi Removido nada!");
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//GETTERS e SETTERS
	
	public boolean isLogado() {
		return logado;
	}
	public void setLogado(boolean logado) {
		this.logado = logado;
	}
	public String getSenha2() {
		return senha2;
	}
	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public Boolean getLogado() {
		return logado;
	}
	public void setLogado(Boolean logado) {
		this.logado = logado;
	}
	public String getProxPagina() {
		return proxPagina;
	}
	public void setProxPagina(String proxPagina) {
		this.proxPagina = proxPagina;
	}
}