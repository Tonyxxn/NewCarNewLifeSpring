package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Distrito;
import pe.edu.upc.repositories.IDistritoRepository;
import pe.edu.upc.serviceinterfaces.IDistritoService;

@Service
public class DistritoServiceImpl implements IDistritoService {

	@Autowired
	public IDistritoRepository dR;

	@Override
	public int insert(Distrito distrito) {
		// TODO Auto-generated method stub
		int rpta= dR.buscarDistrito(distrito.getNameDistrito());
		System.out.println("buscar "+distrito.getNameDistrito()+": "+rpta);
		if(distrito.getIdDistrito()==0) {
            if(rpta==0) {
                dR.save(distrito);
            }
        }
        else {
            dR.save(distrito);
            rpta=0;
        }

		return rpta;
	}

	@Override
	public List<Distrito> list() {
		// TODO Auto-generated method stub
		return dR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Distrito listDistrito(int idDistrito) {
		// TODO Auto-generated method stub
		Optional<Distrito> opd = dR.findById(idDistrito);
		return opd.isPresent() ? opd.get() : new Distrito();
	}

	@Override
	public void delete(int idDistrito) {
		// TODO Auto-generated method stub
		dR.deleteById(idDistrito);
	}

	@Override
	public Optional<Distrito> listarId(int idDistrito) {
		// TODO Auto-generated method stub
		return dR.findById(idDistrito);
	}

	@Override
	public List<Distrito> findByNameDistrito(String nombre) {
		// TODO Auto-generated method stub
		return dR.findByNameDistrito(nombre);
	}

}
