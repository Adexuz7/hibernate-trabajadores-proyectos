package aed.hibernate.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trabajadorProyecto")
public class TrabajadorProyecto implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "dni")
	private Trabajador trabajador;

	@Id
	@ManyToOne
	@JoinColumn(name = "codProyecto")
	private Proyecto proyecto;

	@Column(name = "fechaInicioTrabajador")
	private Date fechaInicioTrabajador;

	public Date getFechaInicioTrabajador() {
		return fechaInicioTrabajador;
	}

	public void setFechaInicioTrabajador(Date fechaInicioTrabajador) {
		this.fechaInicioTrabajador = fechaInicioTrabajador;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String toString() {
		String result = "";

		result += "Trabajador: ";
		result += this.getTrabajador().getNombre();
		result += " - ";
		result += "Proyecto: ";
		result += this.getProyecto().getNomProyecto();
		result += " - ";
		result += "Departamento: ";
		result += this.getProyecto().getDepartamento().getNomDepartamento();
		result += " - ";
		result += "Inicio proyecto: ";
		result += this.getProyecto().getFechaInicioProyecto();
		result += " - ";
		result += "Incorporación trabajador: ";
		result += this.getFechaInicioTrabajador();

		return result;
	}

}
