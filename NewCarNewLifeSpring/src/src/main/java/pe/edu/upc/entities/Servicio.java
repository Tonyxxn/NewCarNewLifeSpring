package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "servicios")
public class Servicio {

	//////////////////////////////////////////////////////////////////////////

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CServicio;

	@Column(name = "NServicio", length = 40, nullable = false)
	private String NServicio;

	@Positive
	@Column(name = "MPrecio", length = 40, nullable = false)
	private int MPrecio;

	@Column(name = "TDescripcion", length = 40, nullable = false)
	private String TDescripcion;

	@ManyToOne
	@JoinColumn(name = "tipoServicioCtipo", nullable = false)
	private TipoServicio tipoServicioCtipo;

	@ManyToOne
	@JoinColumn(name = "tallerMecanicoID", nullable = false)
	private TallerMecanico tallerMecanicoID;

	//////////////////////////////////////////////////////////////////////////

	public Servicio() {
		super();

	}

	public Servicio(int cServicio, String nServicio, @Positive int mPrecio, String tDescripcion,
			TipoServicio tipoServicioCtipo, TallerMecanico tallerMecanicoID) {
		super();
		CServicio = cServicio;
		NServicio = nServicio;
		MPrecio = mPrecio;
		TDescripcion = tDescripcion;
		this.tipoServicioCtipo = tipoServicioCtipo;
		this.tallerMecanicoID = tallerMecanicoID;
	}
	
	//////////////////////////////////////////////////////////////////////////

	public int getCServicio() {
		return CServicio;
	}

	public void setCServicio(int cServicio) {
		CServicio = cServicio;
	}

	public String getNServicio() {
		return NServicio;
	}

	public void setNServicio(String nServicio) {
		NServicio = nServicio;
	}

	public int getMPrecio() {
		return MPrecio;
	}

	public void setMPrecio(int mPrecio) {
		MPrecio = mPrecio;
	}

	public String getTDescripcion() {
		return TDescripcion;
	}

	public void setTDescripcion(String tDescripcion) {
		TDescripcion = tDescripcion;
	}

	public TipoServicio getTipoServicioCtipo() {
		return tipoServicioCtipo;
	}

	public void setTipoServicioCtipo(TipoServicio tipoServicioCtipo) {
		this.tipoServicioCtipo = tipoServicioCtipo;
	}

	public TallerMecanico getTallerMecanicoID() {
		return tallerMecanicoID;
	}

	public void setTallerMecanicoID(TallerMecanico tallerMecanicoID) {
		this.tallerMecanicoID = tallerMecanicoID;
	}

	//////////////////////////////////////////////////////////////////////////

}
