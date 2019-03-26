
package model.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.entity.Usuario;

@Stateless
public class UsuarioDB extends Dao{
	
	public Usuario validarLogin(String email,String senha) {
		System.out.println("EMAIL: "+email+" SENHA: "+senha);
		TypedQuery<Usuario> query = criarTypedQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha",Usuario.class);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		try {
			Usuario user = query.getSingleResult();
			System.out.println("Email e Senha conferidos do usuario: "+user.getNome());
			return user;
		}catch (NoResultException e) {
			// TODO: adicionar debug ao tratamento de exeção
			System.out.println("Email ou senha incorretos!");
			return null;
		}
	}
}
