package aed.hibernate.app;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import aed.hibernate.models.Departamento;
import aed.hibernate.models.Proyecto;
import aed.hibernate.models.Trabajador;
import aed.hibernate.models.TrabajadorNomina;
import aed.hibernate.models.TrabajadorProyecto;
import aed.hibernate.util.HibernateUtil;

public class Main {

	private static Session session;

	public static void main(String[] args) {

		session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		try {

			// Departamentos
			Departamento dep1 = new Departamento(1, "Departamento 1", "000123456789");
			Departamento dep2 = new Departamento(2, "Departamento 2", "000222222222");
			Departamento dep3 = new Departamento(3, "Departamento 3", "000333333333");

			session.save(dep1);
			session.save(dep2);
			session.save(dep3);

			// Proyectos
			Proyecto pro1 = new Proyecto();
			pro1.setCodProyecto("PRO1");
			pro1.setNomProyecto("Proyecto 1");
			pro1.setFechaInicioProyecto(Date.valueOf("2021-02-07"));
			pro1.setDepartamento(dep1);

			session.save(pro1);

			// Trabajador
			Trabajador tra1 = new Trabajador();
			tra1.setDni("12345678E");
			tra1.setNombre("Trabajador 1");
			tra1.setTelefono("111111111111");
			tra1.setActivo("1");

			session.save(tra1);

			// Tabajador Nomina
			TrabajadorNomina nom1 = new TrabajadorNomina();
			nom1.setTrabajador(tra1);
			nom1.setSalario(1000.99);
			nom1.setTipoContrato("Fijo");
			tra1.setNomina(nom1);

			session.save(nom1);

			// Trabajador Proyecto
			TrabajadorProyecto trabajadorProyecto1 = new TrabajadorProyecto();
			trabajadorProyecto1.setTrabajador(tra1);
			// trabajadorProyecto1.setProyecto(pro1);
			trabajadorProyecto1.setProyecto(pro1);
			trabajadorProyecto1.setFechaInicioTrabajador(Date.valueOf("2021-03-03"));

			// List<TrabajadorProyecto> proyectos1 = new ArrayList<TrabajadorProyecto>();
			// proyectos1.add(trabajadorProyecto1);

			// tra1.setProyectos(proyectos1);
			session.update(tra1);

			session.save(trabajadorProyecto1);

			session.getTransaction().commit();

			// MENU

			Scanner sc = new Scanner(System.in);

			System.out.println("Selecciona una opción:");
			System.out.println("(1) Visualizar todos los trabajadores con todos sus datos, incluyendo los de nominas.");
			System.out.println(
					"(2) Visualizar todos los trabajadores con salario mayor a uno introducido por teclado y que estén en activo.");
			System.out.println(
					"(3) Visualizar para un determinado trabajador todos los proyectos en los que ha estado trabajando");
			System.out.println(
					"(4) Visualizar para un determinado trabajador la cantidad de proyectos que tiene asignado.");
			System.out.println(
					"(5) Visualizar por departamentos los nombres de los departamentos, los nombres de los proyectos que tienen\n"
							+ "con sus fechas de inicio.");
			System.out.println("(6) Visualizar los proyectos del departamento x e y");

			int op = sc.nextInt();

			sc.nextLine();

			switch (op) {

			case 1:

				Query q = session.createQuery("FROM Trabajador");
				List<Trabajador> trabajadoresList = q.getResultList();

				for (Trabajador trabajador : trabajadoresList) {
					System.out.println(trabajador);
				}

				break;

			case 2:

				System.out.println("Introduce salario:");
				int salary = sc.nextInt();

				Query q2 = session.createQuery("FROM Trabajador " + "WHERE activo = 1 AND dni IN (" + "SELECT dni "
						+ "FROM TrabajadorNomina " + "WHERE salario > " + salary + ")");
				List<Trabajador> trabajadoresList2 = q2.getResultList();

				for (Trabajador trabajador : trabajadoresList2) {
					System.out.println(trabajador);
				}

				break;

			case 3:

				String pattern = "\\d{8}[A-Z]";

				System.out.println("Introduce DNI de empleado:");
				String employee3 = sc.nextLine();

				if (!employee3.matches(pattern)) {
					System.out.println("DNI no válido");
					break;
				}

				Query q3 = session.createQuery("FROM TrabajadorProyecto " + "WHERE dni = '" + employee3 + "'");

				List<TrabajadorProyecto> proyectos = q3.getResultList();

				for (TrabajadorProyecto proyecto : proyectos) {
					System.out.println(proyecto);
				}

				break;

			case 4:

				System.out.println("Introduce DNI de empleado:");
				String employee4 = sc.nextLine();
				// SELECT COUNT(codProyecto) FROM trabajadorProyecto WHERE dni = '11111111111A'

				Query q4 = session.createQuery(
						"select count(codProyecto) from TrabajadorProyecto where dni = '" + employee4 + "'");

				// q4.setParameter("dni", employee4);

				int count = Integer.parseInt(q4.uniqueResult().toString());

				System.out.println(count);

				break;

			case 5:

				Query q51 = session.createQuery("from Departamento");

				List<Departamento> departamentos = q51.getResultList();

				for (Departamento departamento : departamentos) {
					System.out.println(departamento.getNomDepartamento());

					Query q52 = session
							.createQuery("from Proyecto where codDepartamento = " + departamento.getCodDepartamento());

					List<Proyecto> proyectos52 = q52.getResultList();

					if (proyectos52.isEmpty()) {
						System.out.println("* No hay proyectos *");
					}

					for (Proyecto proyecto52 : proyectos52) {
						System.out.println("* Proyecto: " + proyecto52.getNomProyecto() + " | Fecha de inicio: "
								+ proyecto52.getFechaInicioProyecto());
					}

				}

				break;

			case 6:

				System.out.println("Código de departamento x:");
				int codDep1 = sc.nextInt();
				sc.nextLine();

				System.out.println("Código de departamento y:");
				int codDep2 = sc.nextInt();
				sc.nextLine();

				printDepartmentProjects(codDep1);
				printDepartmentProjects(codDep2);

				break;

			}

			sc.close();

			// END MENU

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

	}

	private static void printDepartmentProjects(int departmentCode) {
		Query q1 = session.createQuery("from Departamento where codDepartamento = " + departmentCode);

		List<Departamento> departamentos = q1.getResultList();

		for (Departamento departamento : departamentos) {
			System.out.println(departamento.getNomDepartamento());

			Query q2 = session
					.createQuery("from Proyecto where codDepartamento = " + departamento.getCodDepartamento());

			List<Proyecto> proyectos = q2.getResultList();

			if (proyectos.isEmpty()) {
				System.out.println("* No hay proyectos *");
			}

			for (Proyecto proyecto : proyectos) {
				System.out.println("* Proyecto: " + proyecto.getNomProyecto() + " | Fecha de inicio: "
						+ proyecto.getFechaInicioProyecto());
			}

		}
	}

}
