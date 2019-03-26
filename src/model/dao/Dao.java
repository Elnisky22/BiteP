package model.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public abstract class Dao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;
	
	protected Query criarQuery(String sql) {
		return em.createQuery(sql);
	}
	
	protected <T> TypedQuery<T> criarTypedQuery(String sql, Class<T> classe) {
		return em.createQuery(sql, classe);
	}
	
	public <T> T consultarID (Class<T> classe, Object key){
		return em.find(classe, key);
	}
	
	public <T> void cadastrar(T entity) {
		em.persist(entity);
	} 
	
	public <T> void alterar(T entity) {
		em.merge(entity);
	}
	
	public <T> void remover(T entity) {
		em.remove(em.merge(entity));
	}	
}
