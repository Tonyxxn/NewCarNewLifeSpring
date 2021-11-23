package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.CitaEstado;

public interface ICitaEstadoService {
	public Integer insert(CitaEstado citaestado);

	List<CitaEstado> list();
}
