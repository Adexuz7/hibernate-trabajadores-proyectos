package aed.hibernate.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trabajadores")
public class Trabajador {

	@Id
	@Column(name = "dni", length = 12)
	private String dni;

	@Column(name = "nombre", length = 30)
	private String nombre;

	@Column(name = "telefono", length = 12)
	private String telefono;

	@Column(name = "activo", columnDefinition = "char(1)")
	private String activo;

	@OneToOne(mappedBy = "trabajador")
	private TrabajadorNomina nomina;

	@OneToMany(mappedBy = "trabajador")
	private List<TrabajadorProyecto> proyectos;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public TrabajadorNomina getNomina() {
		return nomina;
	}

	public void setNomina(TrabajadorNomina nomina) {
		this.nomina = nomina;
	}

	public List<TrabajadorProyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<TrabajadorProyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public Trabajador() {

	}

	public Trabajador(String dni, String nombre, String telefono, String activo) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.activo = activo;
	}

	public String toString() {
		String result = "";

		result += "DNI: ";
		result += this.getDni();
		result += " - ";
		result += "Nombre: ";
		result += this.getNombre();
		result += " - ";
		result += "Teléfono: ";
		result += this.getTelefono();
		result += " - ";
		result += "Activo: ";
		result += this.getActivo();
		result += " - ";
		result += "Contrato: ";
		result += this.getNomina().getTipoContrato();
		result += " - ";
		result += "Salario: ";
		result += this.getNomina().getSalario();

		return result;
	}

}
