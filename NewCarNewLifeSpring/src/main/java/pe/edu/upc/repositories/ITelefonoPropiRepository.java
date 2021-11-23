package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TelefonoPropietario;

@Repository
public interface ITelefonoPropiRepository extends JpaRepository<TelefonoPropietario, Long>{

	@Query("select count(tel.ttelefono) from TelefonoPropietario tel where tel.ttelefono=:numero")
	public int buscarTelefonoPropietario(@Param("numero") String num);
	
	@Query("select p from TelefonoPropietario p where p.ttelefono like %?1%")
	List<TelefonoPropietario> fetchTelByName(String nombrePropropietario);

	@Query("select p from TelefonoPropietario p where p.propietario.npropietario like %?1%")
	public List<TelefonoPropietario> findTelefonoByNameProp(String nameCategory);

	@Query("select p from TelefonoPropietario p where p.ttelefono like %:nombrePropropietario%")
	public List<TelefonoPropietario> findByTelLikeIgnoreCase(String nombrePropropietario);
	
}
