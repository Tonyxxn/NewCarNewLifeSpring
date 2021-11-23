package pe.edu.upc.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CitaEstado")
public class CitaEstado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Cdetalle;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date Ddetalle;

	@ManyToOne
	@JoinColumn(name = "Cestado", nullable = false)
	private Estado estado_Cestado;

	public CitaEstado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CitaEstado(int cdetalle, Date ddetalle, Estado estado_Cestado) {
		super();
		Cdetalle = cdetalle;
		Ddetalle = ddetalle;
		this.estado_Cestado = estado_Cestado;
	}

	public int getCdetalle() {
		return Cdetalle;
	}

	public void setCdetalle(int cdetalle) {
		Cdetalle = cdetalle;
	}

	public Date getDdetalle() {
		return Ddetalle;
	}

	public void setDdetalle(Date ddetalle) {
		Ddetalle = ddetalle;
	}

	public Estado getEstado_Cestado() {
		return estado_Cestado;
	}

	public void setEstado_Cestado(Estado estado_Cestado) {
		this.estado_Cestado = estado_Cestado;
	}

}
