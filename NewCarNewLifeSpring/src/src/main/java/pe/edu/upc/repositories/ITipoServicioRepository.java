package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TipoServicio;

@Repository
public interface ITipoServicioRepository extends JpaRepository<TipoServicio, Integer> {

	@Query("select count(c.NTipoServicio) from TipoServicio c where c.NTipoServicio=:clave")
	public int buscarTipoServicio(@Param("clave") String name);

}
