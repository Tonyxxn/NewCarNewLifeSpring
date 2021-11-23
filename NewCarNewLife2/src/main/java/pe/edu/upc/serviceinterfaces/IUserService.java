package pe.edu.upc.serviceinterfaces;

import java.util.List;

import pe.edu.upc.entities.Users;

public interface IUserService {
	public Integer insert(Users user);

	List<Users> list();

}
