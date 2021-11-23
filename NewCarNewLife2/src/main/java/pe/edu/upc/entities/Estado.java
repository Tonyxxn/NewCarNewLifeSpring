package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Estados")
public class Estado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Cestado;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Estado no puede contener un número")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la Estado no puede contener un número")
	@Column(name = "Nestado", length = 35, nullable = false)
	private String Nestado;

	public Estado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Estado(int cestado, String nestado) {
		super();
		Cestado = cestado;
		Nestado = nestado;
	}

	public int getCestado() {
		return Cestado;
	}

	public void setCestado(int cestado) {
		Cestado = cestado;
	}

	public String getNestado() {
		return Nestado;
	}

	public void setNestado(String nestado) {
		Nestado = nestado;
	}

}
