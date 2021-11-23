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
@Table(name = "Telefonopropietario")
public class TelefonoPropietario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ctelefonoprop;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El telefono no puede contener caracteres especiales")
	@Pattern(regexp = "[^a-z-A-Z]+", message = "El telefono no puede contener letras")
	@Column(name = "tTelefono", length = 10, nullable = false)
	private String ttelefono;
	
	@ManyToOne
	@JoinColumn(name="cPropietario", nullable=false)
	private Propietario propietario;

	public TelefonoPropietario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TelefonoPropietario(long ctelefonoprop,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El telefono no puede contener caracteres especiales") @Pattern(regexp = "[^a-z-A-Z]+", message = "El telefono no puede contener letras") String ttelefono,
			Propietario propietario) {
		super();
		this.ctelefonoprop = ctelefonoprop;
		this.ttelefono = ttelefono;
		this.propietario = propietario;
	}

	public long getCtelefonoprop() {
		return ctelefonoprop;
	}

	public void setCtelefonoprop(int ctelefonoprop) {
		this.ctelefonoprop = ctelefonoprop;
	}

	public String getTtelefono() {
		return ttelefono;
	}

	public void setTtelefono(String ttelefono) {
		this.ttelefono = ttelefono;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

}
