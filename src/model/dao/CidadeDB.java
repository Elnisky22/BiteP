package model.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import model.entity.Cidade;
import model.entity.Estado;

@Stateless
public class CidadeDB extends Dao{
	 public List<Cidade> listarCidade(Integer estadoId){
		try {
			TypedQuery<Cidade> query = criarTypedQuery("SELECT c FROM Cidade c WHERE c.estado.id = :id",Cidade.class);
			query.setParameter("id",estadoId); 
			return query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer pegarEstadoId(Integer id) {
		try {
			TypedQuery<Cidade> query = criarTypedQuery("SELECT c.estado.id FROM Cidade c WHERE c.id = :id",Cidade.class);
			query.setParameter("id", id);
			return query.getSingleResult().getId();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}
