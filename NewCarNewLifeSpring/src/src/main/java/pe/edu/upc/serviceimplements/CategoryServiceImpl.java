package pe.edu.upc.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entities.Category;
import pe.edu.upc.repositories.ICategoryRepository;
import pe.edu.upc.serviceinterfaces.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryRepository cR;

	@Override
	@Transactional
	public Integer insert(Category category) {
		// TODO Auto-generated method stub
		int rpta = cR.buscarCategoria(category.getNameCategory());
		System.out.println("buscar "+category.getNameCategory()+": "+rpta);
		if(category.getIdCategory()==0) {
			if(rpta==0) {
				cR.save(category);
			}
		}
		else {
			cR.save(category);
			rpta=0;
		}

			
		return rpta;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> list() {
		// TODO Auto-generated method stub
		return cR.findAll(Sort.by(Sort.Direction.DESC, "nameCategory"));
	}

	@Override
	public Optional<Category> listarId(long idCategory) {
		// TODO Auto-generated method stub
		return cR.findById(idCategory);
	}

	@Override
	public List<Category> findByName(String name) {
		// TODO Auto-generated method stub
		return cR.findByName(name);
	}

	@Override
	public List<Category> findByNameLikeIgnoreCase(String name) {
		// TODO Auto-generated method stub
		return cR.findByNameLikeIgnoreCase(name);
	}

	@Override
	@Transactional
	public void delete(long idCategory) {
		// TODO Auto-generated method stub
		cR.deleteById(idCategory);

	}

}
