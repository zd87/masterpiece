package co.simplon.masterpiece.services;

import co.simplon.masterpiece.dtos.UserDto;

public interface IUserService {

	boolean checkUnicity(String sesameId);

	void create(UserDto newUserDto);
}
