package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.GaleriaTaller;

public interface IGaleriaTallerService {
	public boolean insert(GaleriaTaller galeriataller);

	List<GaleriaTaller> list();

	GaleriaTaller listarId(int idGaleria);
}
