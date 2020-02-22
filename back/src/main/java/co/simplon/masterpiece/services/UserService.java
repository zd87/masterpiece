package co.simplon.masterpiece.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import co.simplon.masterpiece.dtos.UserDto;
import co.simplon.masterpiece.entities.Role;
import co.simplon.masterpiece.entities.User;
import co.simplon.masterpiece.repositories.RoleRepository;
import co.simplon.masterpiece.repositories.UserRepository;

@Service
public class UserService implements IUserService {

	private final UserRepository userRepo;

	private final RoleRepository roleRepo;

	protected UserService(UserRepository userRepo, RoleRepository roleRepo) {
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}

	public boolean checkUnicity(String sesameId) {
		User user = userRepo.findBySesameId(sesameId);
		if (user != null) {
			return true;
		}
		return false;
	}

	public void create(UserDto newUserDto) {
		Role defaultRole = roleRepo.findByDefaultRoleTrue();
		Set<Role> set = new HashSet<Role>();
		set.add(defaultRole);
		User newUser = new User(newUserDto.getSesameId(), newUserDto.getPwd(), set);
		userRepo.save(newUser);

	}

}
