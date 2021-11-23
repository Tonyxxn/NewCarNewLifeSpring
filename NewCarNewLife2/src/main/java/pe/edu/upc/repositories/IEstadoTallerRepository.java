package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.EstadoTaller;

@Repository
public interface IEstadoTallerRepository extends JpaRepository<EstadoTaller, Integer>{

	@Query("select count(e.nameEstadoTaller) from EstadoTaller e where e.nameEstadoTaller=:name")
	public int buscarEstadoTaller(@Param("name") String name);
	
}
