package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TallerMecanico")
public class TallerMecanico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTallerMecanico;
	@Column(name = "nameTaller", length = 50, nullable = false)
	private String nameTaller;
	@Column(name = "direccionTaller", length = 100, nullable = false)
	private String direccionTaller;
	@Column(name = "capacidadTaller", length = 20, nullable = false)
	private int capacidadTaller;
	@Column(name = "latTaller", length = 20, nullable = false)
	private int latTaller;
	@Column(name = "longTaller", length = 20, nullable = false)
	private int longTaller;
	@ManyToOne
	@JoinColumn(name = "distrito", nullable = false)
	private Distrito distrito;
	
	@ManyToOne
	@JoinColumn(name = "estadoTaller", nullable = false)
	private EstadoTaller estadoTaller;
	
	@ManyToOne
	@JoinColumn(name = "category", nullable = false)
	private Category category;

	public TallerMecanico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TallerMecanico(int idTallerMecanico, String nameTaller, String direccionTaller, int capacidadTaller,
			int latTaller, int longTaller, Distrito distrito, EstadoTaller estadoTaller, Category category) {
		super();
		this.idTallerMecanico = idTallerMecanico;
		this.nameTaller = nameTaller;
		this.direccionTaller = direccionTaller;
		this.capacidadTaller = capacidadTaller;
		this.latTaller = latTaller;
		this.longTaller = longTaller;
		this.distrito = distrito;
		this.estadoTaller = estadoTaller;
		this.category = category;
	}

	public int getIdTallerMecanico() {
		return idTallerMecanico;
	}

	public void setIdTallerMecanico(int idTallerMecanico) {
		this.idTallerMecanico = idTallerMecanico;
	}

	public String getNameTaller() {
		return nameTaller;
	}

	public void setNameTaller(String nameTaller) {
		this.nameTaller = nameTaller;
	}

	public String getDireccionTaller() {
		return direccionTaller;
	}

	public void setDireccionTaller(String direccionTaller) {
		this.direccionTaller = direccionTaller;
	}

	public int getCapacidadTaller() {
		return capacidadTaller;
	}

	public void setCapacidadTaller(int capacidadTaller) {
		this.capacidadTaller = capacidadTaller;
	}

	public int getLatTaller() {
		return latTaller;
	}

	public void setLatTaller(int latTaller) {
		this.latTaller = latTaller;
	}

	public int getLongTaller() {
		return longTaller;
	}

	public void setLongTaller(int longTaller) {
		this.longTaller = longTaller;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public EstadoTaller getEstadoTaller() {
		return estadoTaller;
	}

	public void setEstadoTaller(EstadoTaller estadoTaller) {
		this.estadoTaller = estadoTaller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

}
