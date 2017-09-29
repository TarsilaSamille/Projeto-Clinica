package bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import classes.*;
import util.JPAUtil;


@ManagedBean
public class PacienteBean {
	
	private Paciente paciente = new Paciente();
	
	private List<Paciente> pacientes;
	private List<Paciente> pacientesPesquisados;
	
	private String textoDaPesquisa;
	
	public List<Paciente> getPacientesPesquisados() {
		return pacientesPesquisados;
	}

	public void setPacientesPesquisados(List<Paciente> pacientesPesquisados) {
		this.pacientesPesquisados = pacientesPesquisados;
	}
	
	public String getTextoDaPesquisa() {
		return textoDaPesquisa;
	}

	public void setTextoDaPesquisa(String textoDaPesquisa) {
		this.textoDaPesquisa = textoDaPesquisa;
	}

	public Paciente getPaciente(){
		return this.paciente;
	}
	
	public void setPaciente(Paciente umPaciente) {
		this.paciente = umPaciente;
	}
	
	public String salvarPaciente(Paciente umPaciente) {
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(umPaciente);
		em.getTransaction().commit();
		
		em.close();
		
		return "listaDePacientes";
		
	}
	
	
	public List<Paciente> getPacientes() {
		if(this.pacientes == null) {
			EntityManager emde = JPAUtil.getEntityManager();
			
			Query q = emde.createQuery("select a from Paciente a", Paciente.class);
			
			this.pacientes = q.getResultList();
			emde.close();
		}
		
		return pacientes;
	}
	
	
	
	public List<Paciente> pesquisarPaciente() {
		if(this.pacientesPesquisados == null) {
			EntityManager emde = JPAUtil.getEntityManager();
			
			String jpql = "select a from Paciente a where a.nome like CONCAT(:nomePaciente,'%')";			
			Query q = emde.createQuery(jpql, Paciente.class);
			q.setParameter("nomePaciente", this.textoDaPesquisa);
			
			this.pacientesPesquisados = q.getResultList();
			emde.close();
		}
		
		return pacientesPesquisados;
	}
	
	public void apagarPaciente(Paciente umPaciente) {
		EntityManager emde = JPAUtil.getEntityManager();
		
		emde.getTransaction().begin();
		umPaciente = emde.merge(umPaciente); //n faço ideia do que é isso
		emde.remove(umPaciente);
		emde.getTransaction().commit();
		emde.close();
		
	}

}
