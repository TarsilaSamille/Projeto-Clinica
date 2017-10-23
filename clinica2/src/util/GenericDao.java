package util;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



public abstract class GenericDao<T, I extends Serializable> {


	   private Class<T> persistedClass;

	   protected GenericDao() {
	   }

	   protected GenericDao(Class<T> persistedClass) {
	       this();
	       this.persistedClass = persistedClass;
	   }
	   

	   public void salvar(T entity) {
			EntityManager em = JPAUtil.getEntityManager();
		   
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			
			em.close();
			
	   }

	   public void atualizar(T entity) {
		   EntityManager em = JPAUtil.getEntityManager();
		   
			em.getTransaction().begin();
			em.merge(entity);
			em.flush();
			em.getTransaction().commit();
			
			em.close();

	   }
	   
		public void apagar(T entity) {
			EntityManager em = JPAUtil.getEntityManager();
			
			em.getTransaction().begin();
			entity = em.merge(entity); //n faço ideia do que é isso
			em.remove(entity);
			em.getTransaction().commit();
			em.close();
		}

	   public void remover(I id) {
	       T entity = encontrarPorId(id);
	       
	       apagar(entity);
	       
	   }

	   public List<T> listar() {
		   
			EntityManager em = JPAUtil.getEntityManager();

	       CriteriaBuilder builder = em.getCriteriaBuilder();
	       CriteriaQuery<T> query = builder.createQuery(persistedClass);
	       query.from(persistedClass);
	       
	       return em.createQuery(query).getResultList();
	   }

	   public T encontrarPorId(I id) {
			EntityManager em = JPAUtil.getEntityManager();

	       return em.find(persistedClass, id);
	   }
	   
	   
	   public List<T> pesquisarPorNome(String nomeP) {
			EntityManager em = JPAUtil.getEntityManager();

		       CriteriaBuilder builder = em.getCriteriaBuilder();
		       CriteriaQuery<T> query = builder.createQuery(persistedClass);
		       
		       Root<T> from = query.from(persistedClass);
		        
		       Predicate predicate = builder.and();		        
		        
		       // product.name like %productName%
		       if (nomeP != null){
		           predicate = builder.and(predicate, 
		               builder.like(from.<String>get("nome"), "%"+nomeP+"%"));
		       }
		        
		        
		       TypedQuery<T> typedQuery = em.createQuery(
		           query.select(from )
		           .where( predicate )
		           .orderBy(builder.asc(from.get("nome")))
		       );
		       
		       return typedQuery.getResultList();

		}
	   
	   


	   
	   /*
		
		public List<T> pesquisarPaciente() {
			EntityManager em = JPAUtil.getEntityManager();

		       CriteriaBuilder builder = em.getCriteriaBuilder();
		       CriteriaQuery<T> query = builder.createQuery(persistedClass);
		       
		       Root<T> from = query.from(persistedClass);
		        
		       Predicate predicate = builder.and();
		        
		       // product.price > minPrice
		       if (minPrice != null && minPrice > 0){
		            predicate = builder.and(predicate, builder.ge(from.get("price"), minPrice));
		       }
		       // product.price < maxPrice
		       if (maxPrice != null && maxPrice > 0){
		           predicate = builder.and(predicate, 
		               builder.le(from.get("price"), maxPrice));
		       }
		        
		       // product.name like %productName%
		       if (productName != null && productName.length > 2){
		           predicate = builder.and(predicate, 
		               builder.like(from.<String>get("name"), "%"+productName+"%"));
		       }
		        
		       // product.category.name like %categoryName%
		       if (categoryName != null && categoryName.length() > 2){    
		           predicate = builder.and(predicate, 
		               builder.like(
		                   from.join("category").<String>get("name"), 
		                   "%"+categoryName+"%"));
		       }
		        
		       TypedQuery<T> typedQuery = em.createQuery(
		           query.select(from )
		           .where( predicate )
		           .orderBy(builder.asc(from.get("name")))
		       );
		       
		       return typedQuery.getResultList();

		}
		
		
		/*

		
		
		if(this.pacientesPesquisados == null) {
			EntityManager emde = JPAUtil.getEntityManager();
			
			String jpql = "select a from" + Paciente +" a where a.nome like CONCAT(:nomePaciente,'%')";			
			Query q = emde.createQuery(jpql, Paciente.class);
			q.setParameter("nomePaciente", this.textoDaPesquisa);
			
			this.pacientesPesquisados = q.getResultList();
			emde.close();
		}
		
		return pacientesPesquisados;*/
		
	}