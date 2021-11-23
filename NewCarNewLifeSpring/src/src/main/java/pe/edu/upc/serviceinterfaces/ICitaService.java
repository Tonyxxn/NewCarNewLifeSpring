package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Cita;

public interface ICitaService {
	
	public void insertar(Cita cita);
	
	public void eliminar(int idCita);

	List<Cita> listar();
	
	Optional<Cita> listarId(int idCita);

}
