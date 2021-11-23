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
@Table(name = "Distrito")
public class Distrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDistrito;
	@Column(name = "nameDistrito", length = 50, nullable = false)
	private String nameDistrito;
	@ManyToOne
	@JoinColumn(name = "idProvincia", nullable = false)
	private Provincia provincia;

	public Distrito() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Distrito(int idDistrito, String nameDistrito, Provincia provincia) {
		super();
		this.idDistrito = idDistrito;
		this.nameDistrito = nameDistrito;
		this.provincia = provincia;
	}

	public int getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(int idDistrito) {
		this.idDistrito = idDistrito;
	}

	public String getNameDistrito() {
		return nameDistrito;
	}

	public void setNameDistrito(String nameDistrito) {
		this.nameDistrito = nameDistrito;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}
