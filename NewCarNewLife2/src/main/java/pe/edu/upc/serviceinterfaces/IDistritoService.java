package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Distrito;

public interface IDistritoService {

	public int insert(Distrito distrito);

	List<Distrito> list();

	Distrito listDistrito(int idDistrito);

	public void delete(int idDistrito);

	Optional<Distrito> listarId(int idDistrito);

	List<Distrito> findByNameDistrito(String nombre);

}
