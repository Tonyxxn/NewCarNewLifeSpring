package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Marca;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Integer>{

	@Query("select count(m.nameMarca) from Marca m where m.nameMarca=:name")
	public int buscarMarca(@Param("name") String name);
	
	@Query("select m from Marca m where m.nameMarca like %:name%")
	List<Marca> findByName(String name);

	@Query("select m from Marca m where m.nameMarca like %:name%")
	List<Marca> findByNameLikeIgnoreCase(String name);

	
}
