package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.TallerMecanico;

public interface ITallerMecanicoService {

	public int insert(TallerMecanico taller);

	List<TallerMecanico> list();

	TallerMecanico listTaller(int idTallerMecanico);

	public void delete(int idTallerMecanico);

	Optional<TallerMecanico> listId(int idTallerMecanico);

	List<TallerMecanico> findByNameTaller(String nombre);

}
