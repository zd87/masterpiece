package co.simplon.masterpiece.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.simplon.masterpiece.config.CustomUserDetails;
import co.simplon.masterpiece.dtos.UserAuthDto;
import co.simplon.masterpiece.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements ICustomUserDetailsService {

	private final UserRepository userRepo;

	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
//DELETE IF TESTS WELL
//	@Override
//	public UserViewDto getCurrentUserInfo(Long id) {
//		return userRepo.getById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("with id:" + id));
//	}

	@Override
	public CustomUserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		UserAuthDto user = userRepo.findByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException(
						"no user found with login: " + login));
		return new CustomUserDetails(user);
	}
}
