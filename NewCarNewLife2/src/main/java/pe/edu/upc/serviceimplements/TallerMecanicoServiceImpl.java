package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.TallerMecanico;
import pe.edu.upc.repositories.ITallerMecanicoRepository;
import pe.edu.upc.serviceinterfaces.ITallerMecanicoService;

@Service
public class TallerMecanicoServiceImpl implements ITallerMecanicoService {
	@Autowired
	public ITallerMecanicoRepository tR;

	@Override
	public int insert(TallerMecanico taller) {
		// TODO Auto-generated method stub
		int rpta= tR.buscarTallerMecanico(taller.getNameTaller());
		System.out.println("buscar "+taller.getNameTaller()+": "+rpta);
		if(taller.getIdTallerMecanico()==0) {
            if(rpta==0) {
                tR.save(taller);
            }
        }
        else {
            tR.save(taller);
            rpta=0;
        }

		return rpta;
	
	}

	@Override
	public List<TallerMecanico> list() {
		// TODO Auto-generated method stub
		return tR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TallerMecanico listTaller(int idTallerMecanico) {
		// TODO Auto-generated method stub
		Optional<TallerMecanico> opd = tR.findById(idTallerMecanico);
		return opd.isPresent() ? opd.get() : new TallerMecanico();
	}

	@Override
	public void delete(int idTallerMecanico) {
		// TODO Auto-generated method stub
		tR.deleteById(idTallerMecanico);
	}

	@Override
	public Optional<TallerMecanico> listId(int idTallerMecanico) {
		// TODO Auto-generated method stub
		return tR.findById(idTallerMecanico);
	}

	@Override
	public List<TallerMecanico> findByNameTaller(String nombre) {
		// TODO Auto-generated method stub
		return tR.findByNameTaller(nombre);
	}

}
