package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Departamento;

public interface IDepartamentoService {

	public Integer insert(Departamento departamento);

	List<Departamento> list();

	Optional<Departamento> listarId(int idDepartamento);

	List<Departamento> findByNameDepartamento(String nombre);

	public void delete(int idDepartamento);

}
