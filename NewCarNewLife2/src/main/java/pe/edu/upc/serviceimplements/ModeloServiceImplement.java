package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Modelo;
import pe.edu.upc.repositories.IModeloRepository;
import pe.edu.upc.serviceinterfaces.IModeloService;

@Service
public class ModeloServiceImplement implements IModeloService{
	
	@Autowired
	private IModeloRepository mR;
	
	@Override
	public List<Modelo> list() {
		// TODO Auto-generated method stub
		return mR.findAll();
	}
	
	//@Override
	//public boolean insert(Modelo modelo) {
		// TODO Auto-generated method stub
		//Modelo rpta= mR.save(modelo);
		//if ( rpta == null) {
			//return false;
		//} else {
			//return true;
		//}
	//}
	
		
	@Transactional
	public Integer insert(Modelo modelo) {
		// TODO Auto-generated method stub
		int rpta= mR.buscarModelo(modelo.getNameModelo());
		System.out.println("buscar "+modelo.getNameModelo()+": "+rpta);
		if(modelo.getIdModelo()==0) {
            if(rpta==0) {
                mR.save(modelo);
            }
        }
        else {
            mR.save(modelo);
            rpta=0;
        }

		return rpta;
		
	}

	@Transactional(readOnly = true)
	public Optional<Modelo> findById(int idModelo) {
		// TODO Auto-generated method stub 
		return mR.findById(idModelo);
	}

	@Override
	public void eliminar(int idModelo) {
		// TODO Auto-generated method stub
		mR.deleteById(idModelo);
	}



	
}
