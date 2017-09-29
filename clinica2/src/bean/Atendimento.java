package bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Atendimento {
	
	@Id @GeneratedValue
	private long id;
	
	private long medicoId;
	private long pacienteId;

	private String dataDaConsulta;
	private String horaDaConsulta;
	
	private String test;
	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getMedicoId() {
		return medicoId;
	}
	public void setMedicoId(long medicoId) {
		this.medicoId = medicoId;
	}

	public long getPacienteId() {
		return pacienteId;
	}
	public void setPacienteId(long pacienteId) {
		this.pacienteId = pacienteId;
	}
	public String getDataDaConsulta() {
		return dataDaConsulta;
	}
	public void setDataDaConsulta(String dataDaConsulta) {
		this.dataDaConsulta = dataDaConsulta;
	}
	public String getHoraDaConsulta() {
		return horaDaConsulta;
	}
	public void setHoraDaConsulta(String horaDaConsulta) {
		this.horaDaConsulta = horaDaConsulta;
	}
	
	


}

