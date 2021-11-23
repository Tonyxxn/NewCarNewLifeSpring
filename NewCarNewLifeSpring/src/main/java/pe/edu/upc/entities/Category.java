package pe.edu.upc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Category")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idCategory;
	
	@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la Categoría no puede contener caracteres especiales")
	@Pattern(regexp = "[^0-9]+", message = "El nombre de la Categoría no puede contener un número")
	@Column(name = "nameCategory", nullable = false, length = 100)
	private String nameCategory;
	
	@Pattern(regexp = "[^0-9]+", message = "La glosa solo puede tener una letra")
	@Column(name = "nGlosa", nullable = false, length = 10)
	private String nGlosa;
	
	@Column(name = "qValoracion", length = 10, nullable = false)
	private int qValoracion;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(long idCategory,
			@Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre de la Categoría no puede contener caracteres especiales") @Pattern(regexp = "[^0-9]+", message = "El nombre de la Categoría no puede contener un número") String nameCategory,
			@Pattern(regexp = "[^0-9]+", message = "La glosa solo puede tener una letra") String nGlosa,
			int qValoracion) {
		super();
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
		this.nGlosa = nGlosa;
		this.qValoracion = qValoracion;
	}

	public long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public String getnGlosa() {
		return nGlosa;
	}

	public void setnGlosa(String nGlosa) {
		this.nGlosa = nGlosa;
	}

	public int getqValoracion() {
		return qValoracion;
	}

	public void setqValoracion(int qValoracion) {
		this.qValoracion = qValoracion;
	}
	
	

}
