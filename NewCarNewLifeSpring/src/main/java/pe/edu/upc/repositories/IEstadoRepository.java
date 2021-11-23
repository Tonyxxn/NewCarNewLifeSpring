package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Estado;

@Repository
public interface IEstadoRepository extends JpaRepository<Estado, Integer> {
	
	@Query("select count(e.Nestado) from Estado e where e.Nestado=:clave")
	public int BuscarEstado(@Param("clave") String name);

}
