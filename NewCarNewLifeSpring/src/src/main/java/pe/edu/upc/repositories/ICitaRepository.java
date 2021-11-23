package pe.edu.upc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Cita;

@Repository
public interface ICitaRepository extends JpaRepository<Cita, Integer> {
	
	

}
