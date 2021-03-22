package co.simplon.masterpiece.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.simplon.masterpiece.config.ResourceNotFoundException;
import co.simplon.masterpiece.dtos.RoleMgmtUserViewDto;
import co.simplon.masterpiece.dtos.UserCreateDto;
import co.simplon.masterpiece.dtos.UserViewDto;
import co.simplon.masterpiece.entities.Role;
import co.simplon.masterpiece.entities.User;
import co.simplon.masterpiece.repositories.RoleRepository;
import co.simplon.masterpiece.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService implements IUserService {

	private final UserRepository userRepo;

	private final RoleRepository roleRepo;

	private final PasswordEncoder encoder;

	public void create(UserCreateDto newUserDto) {
		// Giving a default role
		Set<Role> set = new HashSet<Role>();
		Role defaultRole = roleRepo.findByDefaultRoleTrue();
		set.add(defaultRole);
		// encoding the password
		String encodedPassword = encoder.encode(newUserDto.getPassword());
		User newUser = new User(newUserDto.getFirstname(), newUserDto.getLastname(),
				newUserDto.getLogin(), encodedPassword, set);
		userRepo.save(newUser);
	}

	public boolean uniqueUserLogin(String value) {
		return !userRepo.existsByLogin(value);
	}

	@Override
	public UserViewDto getCurrentUserInfo(Long id) {
		return userRepo.getById(id)
				.orElseThrow(() -> new ResourceNotFoundException("with id:" + id));
	}

	public List<RoleMgmtUserViewDto> getAllRoleMgmtUser() {
		return userRepo.findAllProjectedBy();
	}
}
