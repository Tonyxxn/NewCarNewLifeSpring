package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.TelefonoPropietario;
import pe.edu.upc.repositories.ITelefonoPropiRepository;
import pe.edu.upc.serviceinterfaces.ITelefonoPropietarioService;

@Service
public class TelefonoPropietarioServiceImpl implements ITelefonoPropietarioService{
	
	@Autowired
	private ITelefonoPropiRepository tR;

	@Override
	@Transactional
	public Integer insert(TelefonoPropietario tel) {
		// TODO Auto-generated method stub
		int telprop = tR.buscarTelefonoPropietario(tel.getTtelefono());
		if(telprop == 0) {
			tR.save(tel);
		} /*else {
			return true;
		}*/
		return telprop;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TelefonoPropietario> list() {
		// TODO Auto-generated method stub
		return tR.findAll(Sort.by(Sort.Direction.ASC, "ttelefono"));
	}

	@Override
	@Transactional(readOnly = true)
	public TelefonoPropietario listarId(long ctel) {
		// TODO Auto-generated method stub
		Optional<TelefonoPropietario> tel = tR.findById(ctel);
		return tel.isPresent() ? tel.get() : new TelefonoPropietario();
	}

	@Override
	@Transactional
	public void delete(long idTel) {
		// TODO Auto-generated method stub
		tR.deleteById(idTel);
	}

	@Override
	public Optional<TelefonoPropietario> findById(Long idTel) {
		// TODO Auto-generated method stub
		return tR.findById(idTel);
	}

	@Override
	public List<TelefonoPropietario> fetchTelefonoByName(String nameProp) {
		// TODO Auto-generated method stub
		return tR.fetchTelByName(nameProp);
	}

	@Override
	public List<TelefonoPropietario> fetchTelefonoByPropietario(String nameCategory) {
		// TODO Auto-generated method stub
		return tR.findTelefonoByNameProp(nameCategory);
	}

	@Override
	public List<TelefonoPropietario> findByNameTelefonoLikeIgnoreCase(String nameProp) {
		// TODO Auto-generated method stub
		return tR.findByTelLikeIgnoreCase(nameProp);
	}
	
	

}
