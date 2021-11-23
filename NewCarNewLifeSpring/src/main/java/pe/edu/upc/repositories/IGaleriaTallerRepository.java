package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.GaleriaTaller;

@Repository
public interface IGaleriaTallerRepository extends JpaRepository<GaleriaTaller, Integer> {
	@Query("select count(l.idGaleria) from GaleriaTaller l where l.idGaleria=:id")
	public int buscarGaleriaTaller(@Param("id") int id);
}
