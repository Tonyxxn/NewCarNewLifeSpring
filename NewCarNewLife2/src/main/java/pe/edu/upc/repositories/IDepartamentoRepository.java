package pe.edu.upc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entities.Departamento;

@Repository
public interface IDepartamentoRepository extends JpaRepository<Departamento, Integer> {

	@Query("select count(d.nameDepartamento) from Departamento d where d.nameDepartamento=:name")
	public int buscarDepartamento(@Param("name") String nombre);

	List<Departamento> findByNameDepartamento(String nombreDepartamento);
}
