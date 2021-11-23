package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Marcas")
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMarca;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del marca no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del marca no puede contener un número")
	@NotEmpty(message = "Ingrese la marca")
	@Column(name = "nameMarca",length = 35, nullable = false)
	private String nameMarca;

	public Marca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Marca(int idMarca, String nameMarca) {
		super();
		this.idMarca = idMarca;
		this.nameMarca = nameMarca;
	}

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public String getNameMarca() {
		return nameMarca;
	}

	public void setNameMarca(String nameMarca) {
		this.nameMarca = nameMarca;
	}
	
	
	
	
}
