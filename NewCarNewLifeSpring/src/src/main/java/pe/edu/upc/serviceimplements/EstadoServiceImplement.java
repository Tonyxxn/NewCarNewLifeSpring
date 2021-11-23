package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Estado;
import pe.edu.upc.repositories.IEstadoRepository;
import pe.edu.upc.serviceinterfaces.IEstadoService;
@Service
public class EstadoServiceImplement implements IEstadoService{

	@Autowired
	private IEstadoRepository eR;
	
	
	@Override
	public Integer insert(Estado estado) {
		int rpta=eR.BuscarEstado(estado.getNestado());
		if(rpta==0) {
			eR.save(estado);
		}
		return rpta;
	}
	
	@Override
	@Transactional
	public void delete(int idEstado) {
		eR.deleteById(idEstado);
	}
	@Override
	public List<Estado> list() {
		// TODO Auto-generated method stub
		return eR.findAll();
	}

		
}
