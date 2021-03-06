package aed.hibernate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "proyectos")
public class Proyecto {

	@Id
	@Column(name = "codProyecto", length = 6)
	private String codProyecto;

	@Column(name = "nomProyecto", length = 20)
	private String nomProyecto;

	@ManyToOne
	@JoinColumn(name = "codDepartamento")
	private Departamento departamento;

	@Column(name = "fechaInicioProyecto")
	private java.sql.Date fechaInicioProyecto;

	@OneToMany(mappedBy = "proyecto")
	private List<TrabajadorProyecto> proyectos;

	public String getCodProyecto() {
		return codProyecto;
	}

	public void setCodProyecto(String codProyecto) {
		this.codProyecto = codProyecto;
	}

	public String getNomProyecto() {
		return nomProyecto;
	}

	public void setNomProyecto(String nomProyecto) {
		this.nomProyecto = nomProyecto;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public java.sql.Date getFechaInicioProyecto() {
		return fechaInicioProyecto;
	}

	public void setFechaInicioProyecto(java.sql.Date fechaInicioProyecto) {
		this.fechaInicioProyecto = fechaInicioProyecto;
	}

	public List<TrabajadorProyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<TrabajadorProyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public String toString() {
		String result = "";

		result += "Proyecto: ";
		result += this.getNomProyecto();
		result += " - ";
		result += "Departamento: ";
		result += this.getDepartamento().getNomDepartamento();
		result += " - ";
		result += "Fecha inicio: ";
		result += this.getFechaInicioProyecto();
		result += " - ";
		//result += "Incorporación trabajador: ";
		//result += this.getProyecto().getFechaInicioTrabajador();

		return result;
	}

}
