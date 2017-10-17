package util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;



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
	       T entity = encontrar(id);
	       
	       apagar(entity);
	       
	   }

	   public List<T> listar() {
		   
			EntityManager em = JPAUtil.getEntityManager();

	       CriteriaBuilder builder = em.getCriteriaBuilder();
	       CriteriaQuery<T> query = builder.createQuery(persistedClass);
	       query.from(persistedClass);
	       
	       return em.createQuery(query).getResultList();
	   }

	   public T encontrar(I id) {
			EntityManager em = JPAUtil.getEntityManager();

	       return em.find(persistedClass, id);
	   }
	}