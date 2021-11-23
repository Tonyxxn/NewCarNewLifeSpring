package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Cita;
import pe.edu.upc.repositories.ICitaRepository;
import pe.edu.upc.serviceinterfaces.ICitaService;

@Service
public class CitaServiceImpl implements ICitaService {
	
	@Autowired
	private ICitaRepository cR;

	@Override
	@Transactional
	public void insertar(Cita cita) {
		cR.save(cita);
	}

	@Override
	@Transactional
	public void eliminar(int idCita) {
		cR.deleteById(idCita);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cita> listar() {
		return cR.findAll();
	}

	@Override
	public Optional<Cita> listarId(int idCita) {
		return cR.findById(idCita);
	}

}
