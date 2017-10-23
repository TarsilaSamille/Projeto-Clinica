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
	
	
	public void pesquisarPaciente() {
		this.setPacientesPesquisados(pesquisarPorNome(this.textoDaPesquisa));
		
    	for(Paciente paciente : this.pacientesPesquisados) {
            System.out.println(paciente.getNome());
        }
    	
	}
	


}
