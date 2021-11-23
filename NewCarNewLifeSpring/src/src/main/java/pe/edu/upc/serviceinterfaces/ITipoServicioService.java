package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.TipoServicio;

public interface ITipoServicioService {

	public Integer insert(TipoServicio tipoServicio);
	
	public void delete(int idTipoServicio);

	List<TipoServicio> list();
	
	Optional<TipoServicio> listarId(int idTipoServicio);

}
