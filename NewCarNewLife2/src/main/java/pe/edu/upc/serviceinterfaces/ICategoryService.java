package pe.edu.upc.serviceinterfaces;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entities.Category;

public interface ICategoryService {
	
	public Integer insert(Category category);
	
	public void delete(long idCategory);

	List<Category> list();
	
	Optional<Category> listarId(long idCategory);
	
	List<Category> findByName(String name);
	
	List<Category> findByNameLikeIgnoreCase(String name);

}
