package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.CitaEstado;
import pe.edu.upc.repositories.ICitaEstadoRepository;
import pe.edu.upc.serviceinterfaces.ICitaEstadoService;

@Service
public class CitaEstadoServiceImplements implements ICitaEstadoService {

	@Autowired
	private ICitaEstadoRepository eC;

	@Override
	public Integer insert(CitaEstado citaestado) {
		int rpta = eC.buscarcitaestado(citaestado.getCdetalle());
		if (rpta == 0) {
			eC.save(citaestado);

		}
		return rpta;
	}

	@Override
	public List<CitaEstado> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
