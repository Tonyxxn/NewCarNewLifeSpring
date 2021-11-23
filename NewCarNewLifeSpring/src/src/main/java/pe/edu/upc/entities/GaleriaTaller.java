package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "GaleriaTalleres")
public class GaleriaTaller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGaleria;
	
	@Column(name = "tArchivo", nullable = true)
	private String tArchivo;

	//@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre del producto no puede contener un caracter especial")
	//@Pattern(regexp = "[^0-9]+", message = "El nombre del producto no puede contener un número")
	@Column(name = "tDescripcion", length = 100, nullable = false)
	private String tDescripcion;

	public GaleriaTaller(int idGaleria, String tArchivo, String tDescripcion) {
		super();
		this.idGaleria = idGaleria;
		this.tArchivo = tArchivo;
		this.tDescripcion = tDescripcion;
	}

	public GaleriaTaller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdGaleria() {
		return idGaleria;
	}

	public void setIdGaleria(int idGaleria) {
		this.idGaleria = idGaleria;
	}

	public String gettArchivo() {
		return tArchivo;
	}

	public void settArchivo(String tArchivo) {
		this.tArchivo = tArchivo;
	}

	public String gettDescripcion() {
		return tDescripcion;
	}

	public void settDescripcion(String tDescripcion) {
		this.tDescripcion = tDescripcion;
	}


	
}
