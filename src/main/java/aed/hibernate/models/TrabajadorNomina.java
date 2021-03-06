package aed.hibernate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trabajadores_nominas")
public class TrabajadorNomina {

	@Id
	@Column(name = "dni", length = 12)
	private String dni;

	@Column(name = "salario", columnDefinition = "numeric(18,2)")
	private double salario;

	@Column(name = "tipoContrato", columnDefinition = "char(10)")
	private String tipoContrato;

	@OneToOne
	@JoinColumn(name = "dni")
	@MapsId
	private Trabajador trabajador;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

}
