package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Provincia;

@Repository
public interface IProvinciaRepository extends JpaRepository<Provincia, Integer> {
	@Query("select count(p.nameProvincia) from Provincia p where p.nameProvincia=:name")
	public int buscarProvincia(@Param("name") String nombre);

	List<Provincia> findByNameProvincia(String nombreProvincia);

}
