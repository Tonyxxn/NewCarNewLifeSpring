package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.CitaEstado;

@Repository
public interface ICitaEstadoRepository extends JpaRepository<CitaEstado, Integer> {
	@Query("select count(l.Cdetalle) from CitaEstado l where l.Cdetalle=:code")
	public int buscarcitaestado(@Param("code") Integer codigo);
}
