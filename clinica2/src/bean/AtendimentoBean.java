package bean;

import javax.faces.bean.ManagedBean;

import util.*;

@ManagedBean
public class AtendimentoBean extends GenericDao< Atendimento, Long> {

	private Atendimento atendimento = new Atendimento();
	
	public AtendimentoBean() {
	    super(Atendimento.class);
	} 
	
	
	public String salvarButao(Atendimento umAtendimento) {
		salvar(umAtendimento);				
		
		return "historicoDeConsultas";
		
	}

	public void setAtendimento(Atendimento umAtendimento) {
		this.atendimento = umAtendimento;
	}


	public Atendimento getAtendimento() {
		return atendimento;
	}

}