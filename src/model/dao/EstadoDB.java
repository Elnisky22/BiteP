package model.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import model.entity.Cidade;
import model.entity.Estado;

@Stateless
public class EstadoDB extends Dao{
	
	public List<Estado> listarEstado(){
		try {
			TypedQuery<Estado> query = criarTypedQuery("SELECT e FROM Estado e",Estado.class);
			return query.getResultList();
		}catch(Exception e) {
			e.getStackTrace();
			return null;
		}
	}
	
	public Estado buscarEstado(Integer id) {
		try {
			TypedQuery<Estado> query = criarTypedQuery("SELECT e.nome FROM Estado e WHERE e.id = :id",Estado.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
