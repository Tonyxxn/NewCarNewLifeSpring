package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Servicio;

public interface IServicioService {

	public boolean insert(Servicio servicio);
	
	public void delete(int idServicio);
	
	List<Servicio> list();
	
	Optional<Servicio> listarId(int CServicio);
	
}
