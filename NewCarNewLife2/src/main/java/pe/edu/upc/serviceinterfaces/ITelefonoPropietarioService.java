package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.TelefonoPropietario;

public interface ITelefonoPropietarioService {
	
	public Integer insert(TelefonoPropietario tel);
	
	List<TelefonoPropietario> list();
	
	public TelefonoPropietario listarId(long ctel);
	
	public void delete(long idTel);
	
	Optional<TelefonoPropietario> findById(Long idTel);

	List<TelefonoPropietario> fetchTelefonoByName(String nameProp);

	public List<TelefonoPropietario> fetchTelefonoByPropietario(String nameCategory);

	public List<TelefonoPropietario> findByNameTelefonoLikeIgnoreCase(String nameProp);

}
