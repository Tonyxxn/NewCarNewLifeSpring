package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Propietario")
public class Propietario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cpropietario;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Propietario no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre del Propietario no puede contener un número")
	@Column(name = "nPropietario", length = 100, nullable = false)
	private String npropietario;

	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El apellido del Propietario no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El apellido del Propietario no puede contener un número")
	@Column(name = "nApellido", length = 100, nullable = false)
	private String napellido;

	@Column(name = "tCorreo", length = 200, nullable = false)
	private String correo;

	@Column(name = "tClave", length = 16, nullable = false)
	private String clave;
	
	@ManyToOne
	@JoinColumn(name="cCategory", nullable= false)
	private Category category;

	public Propietario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Propietario(long cpropietario,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del Propietario no puede contener caracteres especiales") @Pattern(regexp = "[^0-9]+", message = "El nombre del Propietario no puede contener un número") String npropietario,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El apellido del Propietario no puede contener caracteres especiales") @Pattern(regexp = "[^0-9]+", message = "El apellido del Propietario no puede contener un número") String napellido,
			String correo, String clave, Category category) {
		super();
		this.cpropietario = cpropietario;
		this.npropietario = npropietario;
		this.napellido = napellido;
		this.correo = correo;
		this.clave = clave;
		this.category = category;
	}

	public long getCpropietario() {
		return cpropietario;
	}

	public void setCpropietario(int cpropietario) {
		this.cpropietario = cpropietario;
	}

	public String getNpropietario() {
		return npropietario;
	}

	public void setNpropietario(String npropietario) {
		this.npropietario = npropietario;
	}

	public String getNapellido() {
		return napellido;
	}

	public void setNapellido(String napellido) {
		this.napellido = napellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
