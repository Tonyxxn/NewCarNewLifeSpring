package pe.edu.upc.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "citas")
public class Cita {

	////////////////////////////////////////////////////

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CCita;

	@Column(name = "DHCita")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "Ingrese un fecha para la cita")
	@Future(message = "Ingrese una fecha que sea mayor que la de hoy")
	private Date DHCita;

	@Column(name = "DHCreacionCita")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date DHCreacionCita;

	@Column(name = "precio", columnDefinition = "Decimal(8,2)", nullable = false)
	@NotNull(message = "Ingrese un precio")
	private Double precio;

	@ManyToOne
	@JoinColumn(name = "VehiculoPlaca", nullable = false)
	private Vehiculo VehiculoPlaca;

	@ManyToOne
	@JoinColumn(name = "CServicio", nullable = false)
	private Servicio CServicio;
	
	@ManyToOne
	@JoinColumn(name = "CEstado", nullable = false)
	private Estado CEstado;
	
	////////////////////////////////////////////////////

	public int getCCita() {
		return CCita;
	}

	public void setCCita(int cCita) {
		CCita = cCita;
	}

	public Date getDHCita() {
		return DHCita;
	}

	public void setDHCita(Date dHCita) {
		DHCita = dHCita;
	}

	public Date getDHCreacionCita() {
		return DHCreacionCita;
	}

	public void setDHCreacionCita(Date dHCreacionCita) {
		DHCreacionCita = dHCreacionCita;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Vehiculo getVehiculoPlaca() {
		return VehiculoPlaca;
	}

	public void setVehiculoPlaca(Vehiculo vehiculoPlaca) {
		VehiculoPlaca = vehiculoPlaca;
	}

	public Servicio getCServicio() {
		return CServicio;
	}

	public void setCServicio(Servicio cServicio) {
		CServicio = cServicio;
	}
	
	public Estado getCEstado() {
		return CEstado;
	}

	public void setCEstado(Estado cEstado) {
		CEstado = cEstado;
	}

	////////////////////////////////////////////////////

}
