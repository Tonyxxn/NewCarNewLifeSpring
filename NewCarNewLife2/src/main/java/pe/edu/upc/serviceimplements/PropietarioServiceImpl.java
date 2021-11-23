package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Propietario;
import pe.edu.upc.repositories.IPropietarioRepository;
import pe.edu.upc.serviceinterfaces.IPropietarioService;

@Service
public class PropietarioServiceImpl implements IPropietarioService {

	@Autowired
	private IPropietarioRepository pR;

	@Override
	@Transactional
	public Integer insert(Propietario prop) {
		// TODO Auto-generated method stub
		int objProp = pR.buscarPropietario(prop.getNpropietario());
		if (objProp == 0) {
			pR.save(prop);
		} /*else {
			return true;
		}*/
		return objProp;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Propietario> list() {
		// TODO Auto-generated method stub
		return pR.findAll(Sort.by(Sort.Direction.ASC, "npropietario"));
	}

	@Override
	@Transactional
	public void delete(long idProp) {
		// TODO Auto-generated method stub
		pR.deleteById(idProp);
	}

	@Override
	public Optional<Propietario> findById(Long idProp) {
		// TODO Auto-generated method stub
		return pR.findById(idProp);
	}

	@Override
	public List<Propietario> fetchPropietarioByName(String nameProp) {
		// TODO Auto-generated method stub
		return pR.fetchPropietarioByName(nameProp);
	}

	@Override
	public List<Propietario> fetchPropietarioByCategoryName(String nameCategory) {
		// TODO Auto-generated method stub
		return pR.findPropietarioByNameCategory(nameCategory);
	}

	@Override
	public List<Propietario> findByNamePropietarioLikeIgnoreCase(String nameProp) {
		// TODO Auto-generated method stub
		return pR.findByNameLikeIgnoreCase(nameProp);
	}

}
