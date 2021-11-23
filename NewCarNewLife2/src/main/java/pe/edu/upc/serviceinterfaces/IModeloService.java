package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Modelo;

public interface IModeloService {
	
	public Integer insert(Modelo modelo);
	
	//public boolean insert(Modelo modelo);
	
	List<Modelo> list();
	
	public void eliminar(int idModelo);

	Optional<Modelo> findById(int idModelo);
	
}
