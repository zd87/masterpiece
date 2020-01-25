package co.simplon.masterpiece.services;

import co.simplon.masterpiece.entities.User;

public interface IUserService {
	boolean checkUnicity(String sesameId);
	void create(User newUser);
}

