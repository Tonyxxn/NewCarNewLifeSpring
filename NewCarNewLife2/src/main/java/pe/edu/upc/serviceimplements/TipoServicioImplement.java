package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TipoServicio;
import pe.edu.upc.repositories.ITipoServicioRepository;
import pe.edu.upc.serviceinterfaces.ITipoServicioService;

@Service
public class TipoServicioImplement implements ITipoServicioService {

	@Autowired
	private ITipoServicioRepository tsR;

	@Override
	public Integer insert(TipoServicio tipoServicio) {
		int rpta = tsR.buscarTipoServicio(tipoServicio.getNTipoServicio());
		if (rpta == 0) {
			tsR.save(tipoServicio);
		}
		return rpta;
	}

	@Override
	public List<TipoServicio> list() {
		return tsR.findAll();
	}

	@Override
	public void delete(int idTipoServicio) {
		tsR.deleteById(idTipoServicio);
	}

	@Override
	public Optional<TipoServicio> listarId(int idTipoServicio) {
		
		return tsR.findById(idTipoServicio);
	}

}
