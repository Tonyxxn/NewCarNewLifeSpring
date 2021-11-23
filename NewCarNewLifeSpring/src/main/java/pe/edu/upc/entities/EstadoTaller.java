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
@Table(name = "EstadoTalleres")
public class EstadoTaller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEstadoTaller;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del estado no puede contener un caracter especial")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del estado no puede contener un número")	
	@NotEmpty(message = "Ingrese el estado")
	@Column(name = "nameEstadoTaller",length = 35, nullable = false)
	private String nameEstadoTaller;

	public EstadoTaller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EstadoTaller(int idEstadoTaller, String nameEstadoTaller) {
		super();
		this.idEstadoTaller = idEstadoTaller;
		this.nameEstadoTaller = nameEstadoTaller;
	}

	public int getIdEstadoTaller() {
		return idEstadoTaller;
	}

	public void setIdEstadoTaller(int idEstadoTaller) {
		this.idEstadoTaller = idEstadoTaller;
	}

	public String getNameEstadoTaller() {
		return nameEstadoTaller;
	}

	public void setNameEstadoTaller(String nameEstadoTaller) {
		this.nameEstadoTaller = nameEstadoTaller;
	}


	
	
	
	
}
