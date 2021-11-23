package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.EstadoTaller;

public interface IEstadoTallerService {

	public Integer insert(EstadoTaller estadoTaller);

	public void delete(int idEstadoTaller);

	List<EstadoTaller> list();

	Optional<EstadoTaller> findById(int idEstadoTaller);
}
