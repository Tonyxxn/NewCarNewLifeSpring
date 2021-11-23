package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Departamento")
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDepartamento;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del producto no puede contener simbolos")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del producto no puede contener un número")
	@Column(name = "nameDepartamento", length = 50, nullable = false)
	private String nameDepartamento;

	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNameDepartamento() {
		return nameDepartamento;
	}

	public void setNameDepartamento(String nameDepartamento) {
		this.nameDepartamento = nameDepartamento;
	}

	public Departamento(int idDepartamento, String nameDepartamento) {
		super();
		this.idDepartamento = idDepartamento;
		this.nameDepartamento = nameDepartamento;
	}

}
