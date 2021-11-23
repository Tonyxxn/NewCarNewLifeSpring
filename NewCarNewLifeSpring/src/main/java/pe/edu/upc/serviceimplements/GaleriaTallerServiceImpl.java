package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.GaleriaTaller;
import pe.edu.upc.repositories.IGaleriaTallerRepository;
import pe.edu.upc.serviceinterfaces.IGaleriaTallerService;

@Service
public class GaleriaTallerServiceImpl implements IGaleriaTallerService {
	@Autowired
	private IGaleriaTallerRepository gtR;

	@Override
	public List<GaleriaTaller> list() {
		// TODO Auto-generated method stub
		return gtR.findAll();
	}

	@Override
	public boolean insert(GaleriaTaller galeriataller) {
		// TODO Auto-generated method stub
		GaleriaTaller objProduct = gtR.save(galeriataller);
		if (objProduct == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public GaleriaTaller listarId(int idGaleria) {
		// TODO Auto-generated method stub
		Optional<GaleriaTaller> op = gtR.findById(idGaleria);
		return op.isPresent() ? op.get() : new GaleriaTaller();
	}

}
