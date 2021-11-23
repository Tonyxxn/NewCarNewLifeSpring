package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Modelo;

@Repository
public interface IModeloRepository extends JpaRepository<Modelo, Integer>{

	@Query("select count(m.nameModelo) from Modelo m where m.nameModelo=:name")
	public int buscarModelo(@Param("name") String name);
}
