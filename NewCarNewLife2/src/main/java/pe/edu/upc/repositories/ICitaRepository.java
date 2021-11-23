package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Cita;

@Repository
public interface ICitaRepository extends JpaRepository<Cita, Integer> {
	
	@Query( value = "select v.id_vehiculo, c.dhcita "
			+"from vehiculos v "
			+"join citas c on c.vehiculo_placa = v.id_vehiculo " 
			+"where c.cestado=1 and c.dhcita<now()",
            nativeQuery =true)
    public List<String[]> marMasveXaten();

}
