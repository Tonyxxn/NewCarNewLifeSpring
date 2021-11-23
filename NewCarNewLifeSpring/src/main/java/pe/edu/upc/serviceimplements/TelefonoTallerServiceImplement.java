package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.TelefonoTaller;
import pe.edu.upc.repositories.ITelefonoTallerRepository;
import pe.edu.upc.serviceinterfaces.ITelefonoTallerService;

@Service
public class TelefonoTallerServiceImplement implements ITelefonoTallerService{

	@Autowired
	private ITelefonoTallerRepository ttR;

	@Override
	public List<TelefonoTaller> list() {
		// TODO Auto-generated method stub
		return ttR.findAll(Sort.by(Sort.Direction.ASC, "telTaller"));
	}

	@Override
	public Integer insert(TelefonoTaller telefonotaller) {
		// TODO Auto-generated method stub
		int rpta= ttR.buscarTelefonoTaller(telefonotaller.getTelTaller());
		if(rpta==0) {
			ttR.save(telefonotaller);
		}
		return rpta;
	}

	@Override
	public void delete(int idTelTaller) {
		// TODO Auto-generated method stub
		ttR.deleteById(idTelTaller);
	}

	@Override
	public Optional<TelefonoTaller> findById(int idTelTaller) {
		// TODO Auto-generated method stub
		return ttR.findById(idTelTaller);
	}

	
}
