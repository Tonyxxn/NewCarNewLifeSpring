package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Propietario;

public interface IPropietarioService {
	
	public Integer insert(Propietario prop);
	
	List<Propietario> list();
	
	public void delete(long idProp);
	
	Optional<Propietario> findById(Long idProp);

	List<Propietario> fetchPropietarioByName(String nameProp);

	public List<Propietario> fetchPropietarioByCategoryName(String nameCategory);

	public List<Propietario> findByNamePropietarioLikeIgnoreCase(String nameProp);

}
