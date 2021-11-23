package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.EstadoTaller;
import pe.edu.upc.repositories.IEstadoTallerRepository;
import pe.edu.upc.serviceinterfaces.IEstadoTallerService;

@Service
public class EstadoTallerServiceImplement implements IEstadoTallerService{

	@Autowired
	private IEstadoTallerRepository etR;

	@Override
	public List<EstadoTaller> list() {
		// TODO Auto-generated method stub
		return etR.findAll(Sort.by(Sort.Direction.ASC, "nameEstadoTaller"));
	}

	@Override
	public Integer insert(EstadoTaller estadotaller) {
		// TODO Auto-generated method stub
		int rpta= etR.buscarEstadoTaller(estadotaller.getNameEstadoTaller());
		if(rpta==0) {
			etR.save(estadotaller);
		}
		return rpta;
	}

	@Override
	public void delete(int idEstadoTaller) {
		// TODO Auto-generated method stub
		etR.deleteById(idEstadoTaller);
		
	}

	@Override
	public Optional<EstadoTaller> findById(int idEstadoTaller) {
		// TODO Auto-generated method stub
		return etR.findById(idEstadoTaller);
	}

	
}
