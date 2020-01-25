package co.simplon.masterpiece.services;

import org.springframework.stereotype.Service;

import co.simplon.masterpiece.entities.User;
import co.simplon.masterpiece.repositories.UserRepository;
@Service
public class UserService implements IUserService {
	private final UserRepository userRepo;
	protected UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	public boolean checkUnicity(String sesameId) {
		User user = userRepo.findBySesameId(sesameId);
		if (user != null) {
			return true;
		}
		return false;
	}
	public void create(User newUser) {
		userRepo.save(newUser);
		
	}
	
}
