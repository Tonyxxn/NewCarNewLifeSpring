package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Propietario;

@Repository
public interface IPropietarioRepository extends JpaRepository<Propietario, Long>{
	
	@Query("select count(l.npropietario) from Propietario l where l.npropietario=:name ")
	public int buscarPropietario(@Param("name") String nombre);
	
	@Query("select p from Propietario p where p.npropietario like %?1%")
	List<Propietario> fetchPropietarioByName(String nombrePropropietario);

	@Query("select p from Propietario p where p.category.nameCategory like %?1%")
	public List<Propietario> findPropietarioByNameCategory(String nameCategory);

	@Query("select p from Propietario p where p.npropietario like %:nombrePropropietario%")
	public List<Propietario> findByNameLikeIgnoreCase(String nombrePropropietario);

}
