package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Provincia")
public class Provincia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProvincia;

	@Column(name = "nameProvincia", nullable = false, length = 50)
	private String nameProvincia;

	@ManyToOne
	@JoinColumn(name = "idDepartamento", nullable = false)
	private Departamento departamento;

	public Provincia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Provincia(int idProvincia, String nameProvincia, Departamento departamento) {
		super();
		this.idProvincia = idProvincia;
		this.nameProvincia = nameProvincia;
		this.departamento = departamento;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNameProvincia() {
		return nameProvincia;
	}

	public void setNameProvincia(String nameProvincia) {
		this.nameProvincia = nameProvincia;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	
	
}
