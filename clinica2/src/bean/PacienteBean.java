package bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import util.*;

@ManagedBean
public class PacienteBean extends GenericDao< Paciente, Long> {
	
	private Paciente paciente = new Paciente();
	
    public PacienteBean() {
        super(Paciente.class);
     }   
	
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
	
	
	public String salvarButao(Paciente umPaciente) {
		salvar(umPaciente);		
		
		return "listaDePacientes";
		
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
	
	public Paciente pesquisarPacientePorId(long idPaciente) {
		EntityManager em = JPAUtil.getEntityManager();
		Paciente paciente = null;
	    try {
	      //Consulta uma pessoa pelo seu ID.
	    	paciente = em.find(Paciente.class, idPaciente);
	    } finally {
	    	em.close();
	    }
	    return paciente;
	}
	

}
