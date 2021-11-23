package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Estado;

public interface IEstadoService {

	public Integer insert(Estado estado);
	
	List<Estado> list();

	public void delete(int idEstado);
}
