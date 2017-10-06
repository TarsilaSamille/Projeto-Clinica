package bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import util.JPAUtil;


@ManagedBean
public class AtendimentoBean {

	
	private Atendimento atendimento = new Atendimento();
		
	private List<Atendimento> atendimentos;

	
	public Atendimento getAtendimento(){
		return this.atendimento;
		
	}

	public void setAtendimento(Atendimento umAtendimento) {
		this.atendimento = umAtendimento;
	}
	
	public String salvarAtendimento(Atendimento umAtendimento) {
		EntityManager em = JPAUtil.getEntityManager();

		
		em.getTransaction().begin();
		em.persist(umAtendimento);
		em.getTransaction().commit();
		
		em.close();
		
		return "historicoDeConsultas";
		
	}

	public List<Atendimento> getAtendimentos() {
		EntityManager em = JPAUtil.getEntityManager();

		if(this.atendimentos == null) {

			Query q = em.createQuery("select a from Atendimento a", Atendimento.class);
			
			this.atendimentos = q.getResultList();
			em.close();
		}
		
		return atendimentos;
	}
	
	public void apagarAtendimento(Atendimento umAtendimento) {
		EntityManager em = JPAUtil.getEntityManager();

		
		em.getTransaction().begin();
		umAtendimento = em.merge(umAtendimento); 
		em.remove(umAtendimento);
		em.getTransaction().commit();
		em.close();
		
	}

}