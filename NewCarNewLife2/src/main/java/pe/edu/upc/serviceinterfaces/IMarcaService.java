package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Marca;

public interface IMarcaService {

	public Integer insert(Marca marca);

	public void delete(int idMarca);

	List<Marca> list();

	Optional<Marca> findById(int idMarca);
	
	
	List<Marca> findByName(String name);

	List<Marca> findByNameLikeIgnoreCase(String name);

	

	
}
