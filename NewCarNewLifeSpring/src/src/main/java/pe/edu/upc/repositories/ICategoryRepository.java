package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
	
	@Query("select count(l.nameCategory) from Category l where l.nameCategory=:name")
	public int buscarCategoria(@Param("name") String nombre);
	
	@Query("select c from Category c where c.nameCategory like %:name%")
	List<Category> findByName(String name);

	@Query("select c from Category c where c.nameCategory like %:name%")
	List<Category> findByNameLikeIgnoreCase(String name);

}
