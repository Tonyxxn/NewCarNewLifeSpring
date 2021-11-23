package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Servicio;
import pe.edu.upc.repositories.IServicioRepository;
import pe.edu.upc.serviceinterfaces.IServicioService;

@Service
public class ServicioImplement implements IServicioService {

	@Autowired
	private IServicioRepository sR;

	@Override
	public boolean insert(Servicio servicio) {
		Servicio objServicio = sR.save(servicio);
		if (objServicio == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<Servicio> list() {
		return sR.findAll();
	}

	@Override
	public Optional<Servicio> listarId(int CServicio) {
		return sR.findById(CServicio);
	}

	@Override
	public void delete(int idServicio) {
		sR.deleteById(idServicio);
	}

}
