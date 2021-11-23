package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.TelefonoTaller;

@Repository
public interface ITelefonoTallerRepository extends JpaRepository<TelefonoTaller, Integer>{

	@Query("select count(e.telTaller) from TelefonoTaller e where e.telTaller=:name")
	public int buscarTelefonoTaller(@Param("name") String name);
	
}
