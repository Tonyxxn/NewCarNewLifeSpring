package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "TiposServicio")
public class TipoServicio {

	/////////////////////////////////////////////////////////////////////////////////////////

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CTipo;

	@Column(name = "NTipoServicio", length = 40, nullable = false)
	@NotEmpty(message = "Debe ingresar un nombre")
	private String NTipoServicio;

	/////////////////////////////////////////////////////////////////////////////////////////

	public TipoServicio() {
		super();

	}

	public TipoServicio(int cTipo, String nTipoServicio) {
		super();
		CTipo = cTipo;
		NTipoServicio = nTipoServicio;
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	public int getCTipo() {
		return CTipo;
	}

	public void setCTipo(int cTipo) {
		CTipo = cTipo;
	}

	public String getNTipoServicio() {
		return NTipoServicio;
	}

	public void setNTipoServicio(String nTipoServicio) {
		NTipoServicio = nTipoServicio;
	}

}
