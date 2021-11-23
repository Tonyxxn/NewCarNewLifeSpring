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

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "Vehiculos")
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVehiculo;
	
	@ManyToOne
	@JoinColumn(name = "Cmodelo", nullable = false)
	private Modelo modelo;
	
	@ManyToOne
	@JoinColumn(name = "idPropietario", nullable = false)
	private Propietario propietario;
	
	@Column(name = "Qllanta", length = 20, nullable = false)
	private int Qllanta;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date Dfabricacion;

	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Vehiculo(int idVehiculo, Modelo modelo, Propietario propietario, int qllanta, Date dfabricacion) {
		super();
		this.idVehiculo = idVehiculo;
		this.modelo = modelo;
		this.propietario = propietario;
		Qllanta = qllanta;
		Dfabricacion = dfabricacion;
	}



	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public int getQllanta() {
		return Qllanta;
	}

	public void setQllanta(int qllanta) {
		Qllanta = qllanta;
	}

	public Date getDfabricacion() {
		return Dfabricacion;
	}

	public void setDfabricacion(Date dfabricacion) {
		Dfabricacion = dfabricacion;
	}
	
	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	
	
}
