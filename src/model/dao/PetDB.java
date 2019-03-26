package model.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import model.entity.Pet;
@Stateless
public class PetDB extends Dao{
	
	public List<Pet> retornarTodosPets(){
		try {
			TypedQuery<Pet> query = criarTypedQuery("SELECT p FROM Pet p",Pet.class);
			return query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Pet> retornarPetsUsuario(Integer id){
		try {
			TypedQuery<Pet> query = criarTypedQuery("SELECT p FROM Pet p WHERE p.dono.id = :id",Pet.class);
			query.setParameter("id", id);
			return query.getResultList();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Pet> retornarPetsBusca(String nome,String raca,boolean especie1,boolean especie2,boolean genero1,boolean genero2){
		int flag = 0;
		//System.out.println("Agora consultando no metodo!");
		try {
			String sql = "SELECT p FROM Pet p WHERE ";
			
			if(!nome.equals("") || nome.length() > 0) {
				System.out.println("Nome ok!");
				sql += "p.nome = :nome";
				flag = 1;
			}
			if(!raca.equals("") || raca.length() > 0) {
				if(flag == 1) {
					sql += " AND ";
				}
				System.out.println("Raca ok!");
				sql += "p.raca = :raca";
				flag = 1;
			}
			if(especie1) {
				if(flag == 1) {
					sql += " AND ";
				}
				System.out.println("Especie Cachorro ok!");
				sql += "p.especie = 'Cachorro'";
				flag = 1;
			}
			if(especie2) {
				if(flag == 1) {
					sql += " AND ";
				}
				System.out.println("Especie Gato ok!");
				sql += "p.especie = 'Gato'";
				flag = 1;
			}
			if(genero1) {
				if(flag == 1) {
					sql += " AND ";
				}
				System.out.println("M ok!");
				sql += "p.genero = 'M'";
				flag = 1;
			}
			if(genero2) {
				if(flag == 1) {
					sql += " AND ";
				}
				System.out.println("F ok!");
				sql += "p.genero = 'F'";
				flag = 1;
			}
			
			System.out.println("Criando Query...");
			TypedQuery<Pet> query = criarTypedQuery(sql,Pet.class);
			
			if(nome.length()>0) {
				query.setParameter("nome", nome);
			}
			if(raca.length()>0) {
				query.setParameter("raca", raca);
			}
			return query.getResultList();

		}catch(Exception e) {	
			e.printStackTrace();
			return null;
		}
	}
}
