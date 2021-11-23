package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.TelefonoTaller;

public interface ITelefonoTallerService {

	public Integer insert(TelefonoTaller telefonoTaller);

	public void delete(int idTelTaller);

	List<TelefonoTaller> list();

	Optional<TelefonoTaller> findById(int idTelTaller);
}
