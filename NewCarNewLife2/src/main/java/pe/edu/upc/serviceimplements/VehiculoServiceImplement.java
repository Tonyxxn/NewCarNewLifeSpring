package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Vehiculo;
import pe.edu.upc.repositories.IVehiculoRepository;
import pe.edu.upc.serviceinterfaces.IVehiculoService;

@Service
public class VehiculoServiceImplement implements IVehiculoService{
	
	@Autowired
	private IVehiculoRepository vR;

	@Override
	public List<Vehiculo> list() {
		// TODO Auto-generated method stub
		return vR.findAll();
	}

	@Override
	public boolean insert(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		Vehiculo rpta= vR.save(vehiculo);
		if ( rpta == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Vehiculo listarId(int idVehiculo) {
		// TODO Auto-generated method stub
		Optional<Vehiculo> op = vR.findById(idVehiculo);
		return op.isPresent() ? op.get() : new Vehiculo();
	}

	@Override
	public void eliminar(int idVehiculo) {
		// TODO Auto-generated method stub
		vR.deleteById(idVehiculo);
	}

	@Override
	public Optional<Vehiculo> findById(int idVehiculo) {
		// TODO Auto-generated method stub
		return vR.findById(idVehiculo);
	}
	
}
