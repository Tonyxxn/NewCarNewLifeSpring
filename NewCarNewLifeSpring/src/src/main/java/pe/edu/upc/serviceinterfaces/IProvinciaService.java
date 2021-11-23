package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Provincia;

public interface IProvinciaService {

	public int insert(Provincia provincia);

	List<Provincia> list();

	Optional<Provincia> listProvincia(int idProvincia);

	List<Provincia> findByNameProvincia(String nombre);

	public void delete(int idProvincia);

}
