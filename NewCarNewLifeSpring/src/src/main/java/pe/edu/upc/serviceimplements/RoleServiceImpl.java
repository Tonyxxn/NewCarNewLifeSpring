package pe.edu.upc.serviceimplements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entities.Role;
import pe.edu.upc.repositories.RoleRepository;
import pe.edu.upc.serviceinterfaces.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleRepository rR;

	@Override
	public void insert(Role role) {
		rR.save(role);
	}

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}
}
