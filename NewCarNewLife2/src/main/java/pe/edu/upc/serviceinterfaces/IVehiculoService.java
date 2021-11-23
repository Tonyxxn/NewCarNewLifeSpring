package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Vehiculo;

public interface IVehiculoService {
	
	public boolean insert(Vehiculo vehiculo);
	
	List<Vehiculo> list();
	
	Vehiculo listarId(int idVehiculo);
	
	public void eliminar(int idVehiculo);
	
	Optional<Vehiculo> findById(int idVehiculo);
	
}
