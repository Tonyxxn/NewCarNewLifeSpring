package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Marca;
import pe.edu.upc.repositories.IMarcaRepository;
import pe.edu.upc.serviceinterfaces.IMarcaService;

@Service
public class MarcaServiceImplement implements IMarcaService{

	@Autowired
	private IMarcaRepository mR;
	
	@Override
	@Transactional
	public Integer insert(Marca marca) {
		// TODO Auto-generated method stub
		int rpta = mR.buscarMarca(marca.getNameMarca());
		if (rpta == 0) {
			mR.save(marca);
		}
		return rpta;
	}
	
	@Override
	@Transactional
	public void delete(int idMarca) {
		// TODO Auto-generated method stub
		mR.deleteById(idMarca);
	}

	@Override
	public List<Marca> list() {
		// TODO Auto-generated method stub
		return mR.findAll(Sort.by(Sort.Direction.ASC, "nameMarca"));
	}


	@Override
	public Optional<Marca> findById(int idMarca) {
		// TODO Auto-generated method stub
		return mR.findById(idMarca);
	}

	@Override
	public List<Marca> findByName(String name) {
		// TODO Auto-generated method stub
		return mR.findByName(name);
	}

	@Override
	public List<Marca> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return mR.findByNameLikeIgnoreCase(name);
	}

	

	
}
