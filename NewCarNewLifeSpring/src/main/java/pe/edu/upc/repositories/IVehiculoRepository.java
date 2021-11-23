package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Vehiculo;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer>{

	@Query("select count(v.idVehiculo) from Vehiculo v where v.idVehiculo=:id")
	public int buscarModelo(@Param("id") int id);
}
