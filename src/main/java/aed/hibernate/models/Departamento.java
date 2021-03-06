package aed.hibernate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departamentos")
public class Departamento {

	@Id
	@Column(name = "codDepartamento")
	private int codDepartamento;

	@Column(name = "nomDepartamento", length = 20)
	private String nomDepartamento;

	@Column(name = "telefono", length = 12)
	private String telefono;

	public int getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(int codDepartamento) {
		this.codDepartamento = codDepartamento;
	}

	public String getNomDepartamento() {
		return nomDepartamento;
	}

	public void setNomDepartamento(String nomDepartamento) {
		this.nomDepartamento = nomDepartamento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Departamento() {
		
	}

	public Departamento(int codDepartamento, String nomDepartamento, String telefono) {
		this.codDepartamento = codDepartamento;
		this.nomDepartamento = nomDepartamento;
		this.telefono = telefono;
	}

}
