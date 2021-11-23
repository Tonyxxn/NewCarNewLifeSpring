package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TelefonoTalleres")
public class TelefonoTaller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTelTaller;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El telefono no puede contener caracteres especiales")
	@Pattern(regexp = "[^a-z-A-Z]+", message = "El telefono no puede contener letras")
	@Size(min = 8, max = 9)
	@NotEmpty(message = "Ingrese telefono")
	@Column(name = "telTaller",length = 9, nullable = false)
	private String telTaller;

	@ManyToOne
	@JoinColumn(name="idTallerMecanico", nullable=false)
	private TallerMecanico tallermecanico;
	
	public TelefonoTaller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TelefonoTaller(int idTelTaller,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El telefono no puede contener caracteres especiales") @Pattern(regexp = "[^a-z-A-Z]+", message = "El telefono no puede contener letras") @Size(min = 8, max = 9) @NotEmpty(message = "Ingrese telefono") String telTaller,
			TallerMecanico tallermecanico) {
		super();
		this.idTelTaller = idTelTaller;
		this.telTaller = telTaller;
		this.tallermecanico = tallermecanico;
	}



	public TallerMecanico getTallermecanico() {
		return tallermecanico;
	}

	public void setTallermecanico(TallerMecanico tallermecanico) {
		this.tallermecanico = tallermecanico;
	}

	public int getIdTelTaller() {
		return idTelTaller;
	}

	public void setIdTelTaller(int idTelTaller) {
		this.idTelTaller = idTelTaller;
	}

	public String getTelTaller() {
		return telTaller;
	}

	public void setTelTaller(String telTaller) {
		this.telTaller = telTaller;
	}

	
	
	
}
