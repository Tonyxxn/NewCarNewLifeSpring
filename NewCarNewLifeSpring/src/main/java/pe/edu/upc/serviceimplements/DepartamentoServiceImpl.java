package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Departamento;
import pe.edu.upc.repositories.IDepartamentoRepository;
import pe.edu.upc.serviceinterfaces.IDepartamentoService;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

	@Autowired
	private IDepartamentoRepository cR;

	@Override
	public Integer insert(Departamento departamento) {
		// TODO Auto-generated method stub
		int rpta = cR.buscarDepartamento(departamento.getNameDepartamento());
		if (rpta == 0) {
			cR.save(departamento);
		}
		return rpta;
	}

	@Override
	public List<Departamento> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	public Optional<Departamento> listarId(int idDepartamento) {
		// TODO Auto-generated method stub
		return cR.findById(idDepartamento);
	}

	@Override
	public List<Departamento> findByNameDepartamento(String nombre) {
		// TODO Auto-generated method stub
		return cR.findByNameDepartamento(nombre);
	}

	public void delete(int idDepartamento) {
		// TODO Auto-generated method stub
		cR.deleteById(idDepartamento);
	}

}
