package co.simplon.masterpiece.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.simplon.masterpiece.config.CustomUserDetails;
import co.simplon.masterpiece.dtos.UserAuthDto;
import co.simplon.masterpiece.repositories.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements ICustomUserDetailsService {

	private final UserRepository userRepo;

	@Override
	public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserAuthDto user = userRepo.findByLogin(login).orElseThrow(
				() -> new UsernameNotFoundException("no user found with login: " + login));
		return new CustomUserDetails(user);
	}
}
